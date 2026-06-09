package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileInfo;
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

public class FileInfoDeserializer extends ValueDeserializer<FileInfo> {

    private final ObjectMapper objectMapper;

    public FileInfoDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public FileInfo deserialize(JsonParser p, DeserializationContext ctxt) {
        JsonNode treeNode = ctxt.readTree(p);
        Iterable<String> iterable = treeNode.propertyNames();
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
