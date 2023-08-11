package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.GeneralFileExportOptions;
import com.crowdin.client.sourcefiles.model.JavaScriptFileExportOptions;
import com.crowdin.client.sourcefiles.model.PropertyFileExportOptions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileExportOptionsDeserializer extends JsonDeserializer<ExportOptions> {

    private final ObjectMapper objectMapper;

    public FileExportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExportOptions deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
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
