package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class BuildProjectFileTranslationRequest {

    private String targetLanguageId;
    private Boolean exportAsXliff;
}
