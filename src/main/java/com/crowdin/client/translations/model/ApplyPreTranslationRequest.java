package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApplyPreTranslationRequest {

    private List<String> languageIds;
    private List<Long> fileIds;
    private Method method;
    private Long engineId;
    private Long aiPromptId;
    private AutoApproveOption autoApproveOption;
    private Boolean duplicateTranslations;
    private Boolean skipApprovedTranslations;
    private Boolean translateUntranslatedOnly;
    private Integer minimumMatchRatio;
    private Boolean translateWithPerfectMatchOnly;
    private Map<String, List<String>> fallbackLanguages;
    private List<Long> labelIds;
    private List<Long> excludeLabelIds;

}
