package com.crowdin.client.translationmemory.model;

import lombok.Data;

@Data
public class TranslationMemoryExportRequest {

    private String sourceLanguageId;
    private String targetLanguageId;
    private TranslationMemoryFormat format;
}
