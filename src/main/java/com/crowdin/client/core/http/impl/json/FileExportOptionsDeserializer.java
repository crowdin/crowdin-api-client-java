package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.GeneralFileExportOptions;
import com.crowdin.client.sourcefiles.model.PropertyFileExportOptions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FileExportOptionsDeserializer extends JsonDeserializer<ExportOptions> {

    private final ObjectMapper objectMapper;

    public FileExportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExportOptions deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String json = p.getCodec().readTree(p).toString();
        if (json.contains("\"escapeSpecialCharacters\"")) {
            return this.objectMapper.readValue(json, PropertyFileExportOptions.class);
        } else {
            return this.objectMapper.readValue(json, GeneralFileExportOptions.class);
        }
    }
}
