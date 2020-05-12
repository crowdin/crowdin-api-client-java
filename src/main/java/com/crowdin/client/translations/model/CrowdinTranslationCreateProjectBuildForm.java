package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class CrowdinTranslationCreateProjectBuildForm implements BuildProjectTranslationRequest {

    private Long branchId;
    private List<String> targetLanguageIds;
    private Boolean exportTranslatedOnly;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;

}
