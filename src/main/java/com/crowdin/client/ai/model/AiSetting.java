package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AiSetting {

    /**
     * @deprecated no longer part of the API AI settings resource
     */
    @Deprecated
    private Boolean showSuggestion;
    private Long preTranslationAiPromptId;
    private Long editorSuggestionAiPromptId;
    private Long qaCheckActionAiPromptId;
    private Long contextReviewAiPromptId;
    /**
     * Crowdin Enterprise only
     */
    private Long alignmentActionAiPromptId;
    private Double dailyCostLimit;
    private Double monthlyCostLimit;
    private Double userDailyCostLimit;
    private Double userMonthlyCostLimit;
    private Boolean isLimitingActive;
    private List<PerUserOverride> perUserOverrides;

    @Data
    public static class ShortCut {
        private String name;
        private String prompt;
        private Boolean isEnabled;
    }

    @Data
    public static class PerUserOverride {
        private Long userId;
        private AiCostLimitMode costLimitMode;
        private Double dailyCostLimit;
        private Double monthlyCostLimit;
        private Date createdAt;
        private Date updatedAt;
    }

    /**
     * @deprecated no longer part of the API AI settings resource
     */
    @Deprecated
    private List<ShortCut> shortcuts;
}
