package com.crowdin.client.bundles.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportBundleRequest {

    private List<String> targetLanguageIds;
    private Boolean skipUntranslatedStrings;
    private Boolean skipUntranslatedFiles;
    private Boolean exportApprovedOnly;
    private Integer exportWithMinApprovalsCount;
    private Boolean exportStringsThatPassedWorkflow;

}
