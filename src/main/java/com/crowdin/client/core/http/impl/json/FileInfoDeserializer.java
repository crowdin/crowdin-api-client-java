package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileInfoDeserializer extends JsonDeserializer<FileInfo> {

    /**
     * Deserialization context attribute holding the {@code type} of the file whose import/export options are being
     * deserialized. Used by {@link FileImportOptionsDeserializer} and {@link FileExportOptionsDeserializer}.
     */
    public static final String FILE_TYPE_ATTRIBUTE = "crowdin.sourcefiles.fileType";

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
            ObjectReader reader = this.objectMapper.readerFor(File.class);
            String fileType = getFileType(treeNode);
            if (fileType != null) {
                reader = reader.withAttribute(FILE_TYPE_ATTRIBUTE, fileType);
            }
            return reader.readValue(treeNode.toString());
        } else {
            return this.objectMapper.readValue(treeNode.toString(), FileInfo.class);
        }
    }

    private static String getFileType(TreeNode treeNode) {
        TreeNode typeNode = treeNode.get("type");
        if (typeNode instanceof JsonNode && ((JsonNode) typeNode).isTextual()) {
            return ((JsonNode) typeNode).asText();
        }
        return null;
    }

    static String getFileType(DeserializationContext ctxt) {
        Object fileType = ctxt.getAttribute(FILE_TYPE_ATTRIBUTE);
        return fileType instanceof String ? (String) fileType : null;
    }

    static boolean containsAny(List<String> fields, String... values) {
        for (String value : values) {
            if (fields.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
