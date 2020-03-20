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
    private boolean duplicateTranslations;
    private boolean translateUntranslatedOnly;
    private boolean translateWithPerfectMatchOnly;
    private boolean markAddedTranslationsAsDone;

}
