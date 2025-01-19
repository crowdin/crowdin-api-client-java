package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class BuildProjectDirectoryTranslationRequest {

    private List<String> targetLanguageIds;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Boolean exportStringsThatPassedWorkflow;
    private Boolean preserveFolderHierarchy;
}
