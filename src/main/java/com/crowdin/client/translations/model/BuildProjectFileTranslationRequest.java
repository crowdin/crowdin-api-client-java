package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class BuildProjectFileTranslationRequest {

    private String targetLanguageId;
    /**
     * @deprecated deprecated by the API; use
     * {@link com.crowdin.client.translations.TranslationsApi#exportProjectTranslation(Long, ExportProjectTranslationRequest)} instead.
     */
    @Deprecated
    private Boolean exportAsXliff;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Integer exportWithMinApprovalsCount;
    private Boolean exportStringsThatPassedWorkflow;
}
