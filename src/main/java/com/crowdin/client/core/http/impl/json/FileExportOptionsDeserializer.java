package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.GeneralFileExportOptions;
import com.crowdin.client.sourcefiles.model.JavaScriptFileExportOptions;
import com.crowdin.client.sourcefiles.model.PropertyFileExportOptions;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileExportOptionsDeserializer extends ValueDeserializer<ExportOptions> {

    private final ObjectMapper objectMapper;

    public FileExportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExportOptions deserialize(JsonParser p, DeserializationContext ctxt) {
        JsonNode treeNode = ctxt.readTree(p);
        Iterable<String> iterable = treeNode.propertyNames();
        List<String> fields = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        if (fields.contains("escapeSpecialCharacters") || fields.contains("escapeQuotes")) {
            return this.objectMapper.readValue(treeNode.toString(), PropertyFileExportOptions.class);
        } else if (fields.contains("exportQuotes")) {
            return this.objectMapper.readValue(treeNode.toString(), JavaScriptFileExportOptions.class);
        } else {
            return this.objectMapper.readValue(treeNode.toString(), GeneralFileExportOptions.class);
        }
    }
}
