package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplyPreTranslationStringsBasedRequest {

    private List<String> languageIds;
    private List<Long> branchIds;
    private Method method;
    private Long engineId;
    private AutoApproveOption autoApproveOption;
    private Boolean duplicateTranslations;
    private Boolean skipApprovedTranslations;
    private Boolean translateUntranslatedOnly;
    private Boolean translateWithPerfectMatchOnly;
    private Boolean markAddedTranslationsAsDone;
    private List<FallbackLanguage> fallbackLanguages;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;

    @Data
    public static class FallbackLanguage {
        private String languageId;
    }
}
