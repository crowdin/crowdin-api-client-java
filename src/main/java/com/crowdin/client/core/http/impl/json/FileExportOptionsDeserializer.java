package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.DocxFileExportOptions;
import com.crowdin.client.sourcefiles.model.ExportOptions;
import com.crowdin.client.sourcefiles.model.GeneralFileExportOptions;
import com.crowdin.client.sourcefiles.model.JavaScriptFileExportOptions;
import com.crowdin.client.sourcefiles.model.MdFileExportOptions;
import com.crowdin.client.sourcefiles.model.MdxV1FileExportOptions;
import com.crowdin.client.sourcefiles.model.MdxV2FileExportOptions;
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

import static com.crowdin.client.core.http.impl.json.FileInfoDeserializer.containsAny;

/**
 * Resolves the concrete {@link ExportOptions} class.
 * <p>
 * Property and JavaScript options are resolved by their keys as before. All other options resolve to
 * {@link GeneralFileExportOptions} or one of its format-specific subclasses, chosen by the parent file {@code type}
 * (when known) or by format-specific keys.
 */
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
            Class<? extends GeneralFileExportOptions> clazz = resolveByFileType(FileInfoDeserializer.getFileType(ctxt));
            if (clazz == null) {
                clazz = resolveByFields(fields);
            }
            return this.objectMapper.readValue(treeNode.toString(), clazz);
        }
    }

    private static Class<? extends GeneralFileExportOptions> resolveByFileType(String fileType) {
        if (fileType == null) {
            return null;
        }
        switch (fileType) {
            case "md":
                return MdFileExportOptions.class;
            case "mdx_v1":
                return MdxV1FileExportOptions.class;
            case "mdx_v2":
                return MdxV2FileExportOptions.class;
            case "docx":
                return DocxFileExportOptions.class;
            default:
                return null;
        }
    }

    private static Class<? extends GeneralFileExportOptions> resolveByFields(List<String> fields) {
        if (containsAny(fields, "allowWordStyleOptimization", "translateExcelExcludeColors")) {
            return DocxFileExportOptions.class;
        } else if (containsAny(fields, "strongMarker", "emphasisMarker", "unorderedListBullet", "tableColumnWidth", "frontMatterQuotes")) {
            return MdFileExportOptions.class;
        } else {
            return GeneralFileExportOptions.class;
        }
    }
}
