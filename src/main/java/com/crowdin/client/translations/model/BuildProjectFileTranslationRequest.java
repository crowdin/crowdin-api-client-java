package com.crowdin.client.translations.model;

import lombok.Data;

@Data
public class BuildProjectFileTranslationRequest {

    private String targetLanguageId;
    /**
     * @deprecated
     */
    private Boolean exportAsXliff;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Integer exportWithMinApprovalsCount;
    private Boolean exportStringsThatPassedWorkflow;
}
