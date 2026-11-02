package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class ProjectAiSettings {
    /**
     * @deprecated deprecated in the API
     */
    @Deprecated
    private Long preTranslationAiPromptId;
    private Long editorSuggestionAiPromptId;
    /**
     * Crowdin Enterprise only
     */
    private Long alignmentActionAiPromptId;
    private Long qaCheckActionAiPromptId;
    private Long contextReviewAiPromptId;
}
