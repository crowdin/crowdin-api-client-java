package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.Priority;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApplyPreTranslationStringsBasedRequest {

    private List<String> languageIds;
    private List<Long> branchIds;
    /**
     * Applies auto-translation to the strings of the specified task ("By Task" variant).
     * The target language is taken from the task, so {@code languageIds}, {@code branchIds}
     * and label filters should not be set.
     */
    private Long taskId;
    private Method method;
    private Priority priority;
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
    private String translationModifiedAfter;
    private ReplaceTranslationsOption replaceTranslationsOption;
    private Boolean resetApprovalStatus;
    private Boolean notifyOnCompletion;
    /**
     * Enterprise only
     */
    private Integer minimumMatchRatio;
    private Boolean translateWithPerfectMatchOnly;
    /**
     * Legacy list representation of {@code fallbackLanguages}, sent as in SDK 1.37.0 only when
     * {@link #fallbackLanguagesMap} is not set.
     *
     * @deprecated the API documents {@code fallbackLanguages} as an object mapping a language id to its fallback
     * language ids; use {@link #fallbackLanguagesMap} instead.
     */
    @Deprecated
    @JsonIgnore
    private List<FallbackLanguage> fallbackLanguages;
    /**
     * Fallback languages mapping: target language id to the list of its fallback language ids
     * (JSON key {@code fallbackLanguages}). Takes precedence over the legacy {@link #fallbackLanguages} list.
     */
    @JsonIgnore
    private Map<String, List<String>> fallbackLanguagesMap;
    /**
     * Crowdin only
     */
    private String sourceLanguageId;
    /**
     * Custom instruction for AI auto-translation (only for {@link Method#AI})
     */
    private String customInstruction;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;
    private LabelMatchRule labelMatchRule;
    private LabelMatchRule excludeLabelMatchRule;

    /**
     * Writes {@code fallbackLanguages}: the documented mapping if set, otherwise the legacy list
     */
    @JsonGetter("fallbackLanguages")
    @SuppressWarnings("deprecation")
    private Object writeFallbackLanguages() {
        return this.fallbackLanguagesMap != null ? this.fallbackLanguagesMap : this.fallbackLanguages;
    }

    /**
     * @deprecated the API expects an object mapping a language id to its fallback language ids;
     * use {@link ApplyPreTranslationStringsBasedRequest#fallbackLanguagesMap} instead.
     */
    @Deprecated
    @Data
    public static class FallbackLanguage {
        private String languageId;
    }
}
