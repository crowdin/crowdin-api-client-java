package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileInfo;
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

public class FileInfoDeserializer extends JsonDeserializer<FileInfo> {

    private final ObjectMapper objectMapper;

    public FileInfoDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public FileInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
        List<String> fields = StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList());
        if (containsAny(fields, "revisionId", "parserVersion", "priority", "importOptions", "exportOptions", "excludedTargetLanguages", "createdAt", "updatedAt")) {
            return this.objectMapper.readValue(treeNode.toString(), File.class);
        } else {
            return this.objectMapper.readValue(treeNode.toString(), FileInfo.class);
        }
    }

    private static boolean containsAny(List<String> fields, String... values) {
        for (String value : values) {
            if (fields.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
