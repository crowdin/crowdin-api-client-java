package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.crowdin.client.sourcefiles.model.OtherFileImportOptions;
import com.crowdin.client.sourcefiles.model.SpreadsheetFileImportOptions;
import com.crowdin.client.sourcefiles.model.XmlFileImportOptions;
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

public class FileImportOptionsDeserializer extends JsonDeserializer<ImportOptions> {

    private final ObjectMapper objectMapper;

    public FileImportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ImportOptions deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
        List<String> fields = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        if (fields.contains("firstLineContainsHeader")) {
            return this.objectMapper.readValue(treeNode.toString(), SpreadsheetFileImportOptions.class);
        } else if (fields.contains("contentSegmentation")) {
            return this.objectMapper.readValue(treeNode.toString(), OtherFileImportOptions.class);
        } else {
            return this.objectMapper.readValue(treeNode.toString(), XmlFileImportOptions.class);
        }
    }
}
