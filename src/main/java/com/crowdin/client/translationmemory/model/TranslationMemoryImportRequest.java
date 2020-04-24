package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Map;

@Data
public class TranslationMemoryImportRequest {

    private Long storageId;
    private Boolean firstLineContainsHeader;
    private Map<String, Integer> scheme;
}
