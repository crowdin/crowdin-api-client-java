package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.stringtranslations.model.ICULanguageTranslations;
import com.crowdin.client.stringtranslations.model.LanguageTranslations;
import com.crowdin.client.stringtranslations.model.PlainLanguageTranslations;
import com.crowdin.client.stringtranslations.model.PluralLanguageTranslations;
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

public class LanguageTranslationsDeserializer extends JsonDeserializer<LanguageTranslations> {

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
                default: throw new RuntimeException("Wrong type: " + contentType);
            }
        } else {
            throw new RuntimeException("Couldn't get class");
        }
    }
}
