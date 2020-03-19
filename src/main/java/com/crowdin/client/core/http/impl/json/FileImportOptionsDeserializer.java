package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.crowdin.client.sourcefiles.model.OtherFileImportOptions;
import com.crowdin.client.sourcefiles.model.SpreadsheetFileImportOptions;
import com.crowdin.client.sourcefiles.model.XmlFileImportOptions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FileImportOptionsDeserializer extends JsonDeserializer<ImportOptions> {

    private final ObjectMapper objectMapper;

    public FileImportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ImportOptions deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String json = p.getCodec().readTree(p).toString();
        if (json.contains("\"firstLineContainsHeader\"")) {
            return this.objectMapper.readValue(json, SpreadsheetFileImportOptions.class);
        } else if (json.contains("\"contentSegmentation\"")) {
            return this.objectMapper.readValue(json, OtherFileImportOptions.class);
        } else {
            return this.objectMapper.readValue(json, XmlFileImportOptions.class);
        }
    }
}
