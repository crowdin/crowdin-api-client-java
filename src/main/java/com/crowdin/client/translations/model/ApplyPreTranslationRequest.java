package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplyPreTranslationRequest {

    private List<String> languageIds;
    private List<Long> fileIds;
    private Method method;
    private Long engineId;
    private AutoApproveOption autoApproveOption;
    private Boolean duplicateTranslations;
    private Boolean translateUntranslatedOnly;
    private Boolean translateWithPerfectMatchOnly;
    private Boolean markAddedTranslationsAsDone;

}
