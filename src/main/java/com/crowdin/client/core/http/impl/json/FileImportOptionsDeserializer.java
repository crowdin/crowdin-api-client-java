package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.AdocFileImportOptions;
import com.crowdin.client.sourcefiles.model.DocxFileImportOptions;
import com.crowdin.client.sourcefiles.model.HtmlFileImportOptions;
import com.crowdin.client.sourcefiles.model.HtmlWithFrontMatterFileImportOptions;
import com.crowdin.client.sourcefiles.model.IdmlFileImportOptions;
import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.crowdin.client.sourcefiles.model.MdFileImportOptions;
import com.crowdin.client.sourcefiles.model.MdxV1FileImportOptions;
import com.crowdin.client.sourcefiles.model.MdxV2FileImportOptions;
import com.crowdin.client.sourcefiles.model.OtherFileImportOptions;
import com.crowdin.client.sourcefiles.model.SpreadsheetFileImportOptions;
import com.crowdin.client.sourcefiles.model.StringCatalogFileImportOptions;
import com.crowdin.client.sourcefiles.model.VdfFileImportOptions;
import com.crowdin.client.sourcefiles.model.WebXmlFileImportOptions;
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

import static com.crowdin.client.core.http.impl.json.FileInfoDeserializer.containsAny;

/**
 * Resolves the concrete {@link ImportOptions} class.
 * <p>
 * Options with Docx, Spreadsheet or Xml specific keys are resolved as before. All other options resolve to
 * {@link OtherFileImportOptions} or one of its format-specific subclasses, chosen by the parent file {@code type}
 * (when known) or by format-specific keys.
 */
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
        if (fields.contains("cleanTagsAggressively")) {
            return this.objectMapper.readValue(treeNode.toString(), DocxFileImportOptions.class);
        } else if (fields.contains("firstLineContainsHeader")) {
            return this.objectMapper.readValue(treeNode.toString(), SpreadsheetFileImportOptions.class);
        } else if (fields.contains("translateContent")) {
            return this.objectMapper.readValue(treeNode.toString(), XmlFileImportOptions.class);
        } else {
            Class<? extends OtherFileImportOptions> clazz = resolveByFileType(FileInfoDeserializer.getFileType(ctxt));
            if (clazz == null) {
                clazz = resolveByFields(fields);
            }
            return this.objectMapper.readValue(treeNode.toString(), clazz);
        }
    }

    private static Class<? extends OtherFileImportOptions> resolveByFileType(String fileType) {
        if (fileType == null) {
            return null;
        }
        switch (fileType) {
            case "html":
                return HtmlFileImportOptions.class;
            case "fm_html":
                return HtmlWithFrontMatterFileImportOptions.class;
            case "md":
                return MdFileImportOptions.class;
            case "mdx_v1":
                return MdxV1FileImportOptions.class;
            case "mdx_v2":
                return MdxV2FileImportOptions.class;
            case "webxml":
                return WebXmlFileImportOptions.class;
            case "idml":
                return IdmlFileImportOptions.class;
            case "adoc":
                return AdocFileImportOptions.class;
            case "vdf":
                return VdfFileImportOptions.class;
            default:
                return null;
        }
    }

    private static Class<? extends OtherFileImportOptions> resolveByFields(List<String> fields) {
        if (fields.contains("excludedElements")) {
            return fields.contains("excludedFrontMatterElements")
                    ? HtmlWithFrontMatterFileImportOptions.class
                    : HtmlFileImportOptions.class;
        } else if (containsAny(fields, "excludeCodeBlocks", "excludedFrontMatterElements")) {
            return MdFileImportOptions.class;
        } else if (containsAny(fields, "hideAttributeValues", "inlineTags")) {
            return HtmlFileImportOptions.class;
        } else if (fields.contains("inlineHyperlinkText")) {
            return IdmlFileImportOptions.class;
        } else if (fields.contains("importKeyAsSource")) {
            return StringCatalogFileImportOptions.class;
        } else if (fields.contains("excludeIncludeDirectives")) {
            return AdocFileImportOptions.class;
        } else if (containsAny(fields, "convertIcu", "addGenderArgument")) {
            return VdfFileImportOptions.class;
        } else {
            return OtherFileImportOptions.class;
        }
    }
}
