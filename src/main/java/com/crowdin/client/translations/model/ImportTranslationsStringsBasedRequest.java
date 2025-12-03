package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ImportTranslationsStringsBasedRequest {

    private Long storageId;
    private List<String> languageIds;
    private Long branchId;
    private Boolean importEqSuggestions;
    private Boolean autoApproveImported;
    private Boolean translateHidden;
    private Boolean addToTm;
    private ImportTranslationsStringsBasedOptions importOptions;

    @Data
    public static class ImportTranslationsStringsBasedOptions {
        private Map<String, Integer> scheme;
    }
}
