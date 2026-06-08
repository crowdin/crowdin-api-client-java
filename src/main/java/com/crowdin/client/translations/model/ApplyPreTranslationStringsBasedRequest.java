package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplyPreTranslationStringsBasedRequest {

    private List<String> languageIds;
    private List<Long> branchIds;
    private Method method;
    private Long engineId;
    private Long aiPromptId;
    private AutoApproveOption autoApproveOption;
    private Boolean duplicateTranslations;
    private Boolean skipApprovedTranslations;
    /**
     * @deprecated use {@link #scope} instead; cannot be combined with {@code scope}.
     */
    @Deprecated
    private Boolean translateUntranslatedOnly;
    private Scope scope;
    private String translationModifiedBefore;
    private ReplaceTranslationsOption replaceTranslationsOption;
    private Boolean resetApprovalStatus;
    private Integer minimumMatchRatio;
    private Boolean translateWithPerfectMatchOnly;
    private List<FallbackLanguage> fallbackLanguages;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;

    @Data
    public static class FallbackLanguage {
        private String languageId;
    }
}
