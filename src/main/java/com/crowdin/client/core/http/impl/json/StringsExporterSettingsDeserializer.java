package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.AndroidStringsExporterSettings;
import com.crowdin.client.projectsgroups.model.MacOSXStringsExporterSettings;
import com.crowdin.client.projectsgroups.model.StringsExporterSettings;
import com.crowdin.client.projectsgroups.model.StringsExporterSettingsResource;
import com.crowdin.client.projectsgroups.model.XliffStringsExporterSettings;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class StringsExporterSettingsDeserializer extends JsonDeserializer<StringsExporterSettingsResource> {

    private final ObjectMapper objectMapper;

    public StringsExporterSettingsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public StringsExporterSettingsResource deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode parentNode = parser.getCodec().readTree(parser);

        StringsExporterSettingsResource resource =
                this.objectMapper.readValue(parentNode.toString(), StringsExporterSettingsResource.class);

        Class<? extends StringsExporterSettings> classToUse;
        String format = parentNode.get("format").asText();

        switch (format) {
            case "android":
                classToUse = AndroidStringsExporterSettings.class;
                break;
            case "macosx":
                classToUse = MacOSXStringsExporterSettings.class;
                break;
            case "xliff":
                classToUse = XliffStringsExporterSettings.class;
                break;
            default:
                throw new IllegalArgumentException("Format not supported by API client: " + format);
        }

        JsonNode settingsNode = parentNode.get("settings");
        StringsExporterSettings settings = this.objectMapper.readValue(settingsNode.toString(), classToUse);
        resource.setSettings(settings);

        return resource;
    }
}
