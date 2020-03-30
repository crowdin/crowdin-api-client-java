package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.Format;
import lombok.Data;

@Data
public class TranslationMemoryExportRequest {

    private String sourceLanguageId;
    private String targetLanguageId;
    private Format format;
}
