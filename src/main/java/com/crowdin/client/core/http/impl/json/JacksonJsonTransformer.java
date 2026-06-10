package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.projectsgroups.model.FileFormatSettingsResource;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.StringsExporterSettingsResource;
import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.FileInfo;
import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.crowdin.client.stringtranslations.model.LanguageTranslations;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.util.StdDateFormat;
import lombok.SneakyThrows;

import java.util.Date;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper;
    private final ObjectMapper errorObjectMapper;

    public JacksonJsonTransformer() {
        ObjectMapper cleanObjectMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();
        SimpleModule enumModule = new SimpleModule()
            .addDeserializer(Enum.class, new EnumDeserializer());

        SimpleModule module = new SimpleModule()
            .addDeserializer(Date.class, new DateDeserializer())
            .addSerializer(Enum.class, new EnumSerializer())
            .addDeserializer(Enum.class, new EnumDeserializer())
            .addDeserializer(CrowdinApiException.class, new CrowdinApiExceptionDeserializer(cleanObjectMapper))
            .addDeserializer(Project.class, new ProjectDeserializer(cleanObjectMapper.rebuild()
                .addModule(enumModule)
                .build()))
            .addDeserializer(FileInfo.class, new FileInfoDeserializer(cleanObjectMapper.rebuild()
                .addModule(enumModule)
                .addModule(new SimpleModule()
                    .addDeserializer(ImportOptions.class, new FileImportOptionsDeserializer(cleanObjectMapper))
                    .addDeserializer(ExportOptions.class, new FileExportOptionsDeserializer(cleanObjectMapper)))
                .build()))
            .addDeserializer(LanguageTranslations.class, new LanguageTranslationsDeserializer(cleanObjectMapper))
            .addDeserializer(FileFormatSettingsResource.class, new FileFormatSettingsDeserializer(cleanObjectMapper))
            .addDeserializer(StringsExporterSettingsResource.class, new StringsExporterSettingsDeserializer(cleanObjectMapper));
        this.objectMapper = cleanObjectMapper.rebuild()
                .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
                .defaultDateFormat(new StdDateFormat())
                .addModule(module)
                .changeDefaultVisibility(vc -> vc
                    .withVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                    .withVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY))
                .build();
        this.errorObjectMapper = cleanObjectMapper;
    }

    @Override
    @SneakyThrows
    public <T> T parse(String json, Class<T> clazz) {
        if (clazz.equals(HttpException.class) || clazz.equals(HttpBadRequestException.class)) {
            return this.errorObjectMapper.readValue(json, clazz);
        }
        return this.objectMapper.readValue(json, clazz);
    }

    @Override
    @SneakyThrows
    public <T> String convert(T obj) {
        return this.objectMapper.writeValueAsString(obj);
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
