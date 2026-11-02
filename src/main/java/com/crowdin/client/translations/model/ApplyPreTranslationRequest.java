package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApplyPreTranslationRequest {

    private List<String> languageIds;
    private List<Long> fileIds;
    private List<Long> directoryIds;
    private List<Long> branchIds;
    /**
     * Applies auto-translation to the strings of the specified task ("By Task" variant).
     * The target language is taken from the task, so {@code languageIds}, {@code fileIds},
     * {@code directoryIds}, {@code branchIds} and label filters should not be set.
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
    private Map<String, List<String>> fallbackLanguages;
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

}
