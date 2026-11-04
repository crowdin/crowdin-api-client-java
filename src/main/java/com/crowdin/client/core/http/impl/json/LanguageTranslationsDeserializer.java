package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.stringtranslations.model.AssetLanguageTranslations;
import com.crowdin.client.stringtranslations.model.ICULanguageTranslations;
import com.crowdin.client.stringtranslations.model.LanguageTranslations;
import com.crowdin.client.stringtranslations.model.PlainLanguageTranslations;
import com.crowdin.client.stringtranslations.model.PluralLanguageTranslations;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LanguageTranslationsDeserializer extends JsonDeserializer<LanguageTranslations> {

    private static final String CROWDIN_CONTENT_TYPE_PREFIX = "application/vnd.crowdin.";

    private final ObjectMapper objectMapper;

    public LanguageTranslationsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public LanguageTranslations deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
        List<String> fields = StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList());
        if (fields.contains("contentType") && treeNode.get("contentType").isValueNode()) {
            String contentType = treeNode.get("contentType").toString();
            switch (contentType) {
                case "\"text/plain\"": return this.objectMapper.readValue(treeNode.toString(), PlainLanguageTranslations.class);
                case "\"application/vnd.crowdin.text+plural\"": return this.objectMapper.readValue(treeNode.toString(), PluralLanguageTranslations.class);
                case "\"application/vnd.crowdin.text+icu\"": return this.objectMapper.readValue(treeNode.toString(), ICULanguageTranslations.class);
                default:
                    if (isAsset(fields, treeNode.get("contentType"))) {
                        return this.objectMapper.readValue(treeNode.toString(), AssetLanguageTranslations.class);
                    }
                    throw new RuntimeException("Wrong type: " + contentType);
            }
        } else {
            throw new RuntimeException("Couldn't get class");
        }
    }

    /**
     * Asset translations report the MIME type of the asset (e.g. {@code image/jpeg}) as {@code contentType}, so they
     * are recognized by their shape: a non-empty string {@code contentType} that is not one of Crowdin's own
     * {@code application/vnd.crowdin.*} text types, a {@code url}, and neither {@code text} nor {@code plurals}.
     * Any other unknown content type is rejected rather than read as one of the known text types.
     */
    private static boolean isAsset(List<String> fields, TreeNode contentTypeNode) {
        if (contentTypeNode == null || contentTypeNode.asToken() != JsonToken.VALUE_STRING) {
            return false;
        }
        String contentType = ((JsonNode) contentTypeNode).textValue();
        if (contentType == null || contentType.trim().isEmpty() || contentType.startsWith(CROWDIN_CONTENT_TYPE_PREFIX)) {
            return false;
        }
        return fields.contains("url") && !fields.contains("text") && !fields.contains("plurals");
    }
}
