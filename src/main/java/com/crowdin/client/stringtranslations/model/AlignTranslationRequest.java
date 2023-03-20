package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class AlignTranslationRequest {
    private String sourceLanguageId;
    private String targetLanguageId;
    private String text;
}
