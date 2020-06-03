package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper;
    private final ObjectMapper errorObjectMapper;

    public JacksonJsonTransformer() {
        ObjectMapper cleanObjectMapper = new ObjectMapper();
        SimpleModule enumModule = new SimpleModule();
        enumModule.addDeserializer(Enum.class, new EnumDeserializer());
        ObjectMapper skipUnknownPropertiesObjectMapper = new ObjectMapper()
                .registerModule(enumModule)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addSerializer(Enum.class, new EnumSerializer());
        module.addDeserializer(Enum.class, new EnumDeserializer());
        module.addDeserializer(Project.class, new ProjectDeserializer(skipUnknownPropertiesObjectMapper));
        module.addDeserializer(ImportOptions.class, new FileImportOptionsDeserializer(cleanObjectMapper));
        module.addDeserializer(ExportOptions.class, new FileExportOptionsDeserializer(cleanObjectMapper));
        this.objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+hh:mm"))
                .registerModule(module)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
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

}
