package com.crowdin.client.machinetranslationengines.model;

import com.crowdin.client.core.model.EnumConverter;
import lombok.Data;

import java.util.List;

@Data
public class MtTranslateRequest {

    private LanguageRecognitionProvider languageRecognitionProvider;
    private String sourceLanguageId;
    private String targetLanguageId;
    private List<String> strings;

    public enum LanguageRecognitionProvider implements EnumConverter<LanguageRecognitionProvider> {

        CROWDIN, ENGINE;

        public static LanguageRecognitionProvider from(String value) {
            return LanguageRecognitionProvider.valueOf(value.toUpperCase());
        }

        @Override
        public String to(LanguageRecognitionProvider v) {
            return v.name().toLowerCase();
        }
    }
}
