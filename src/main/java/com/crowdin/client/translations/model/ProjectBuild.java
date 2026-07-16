package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectBuild {

    private Long id;
    private Long projectId;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private String createdAt;
    private String updatedAt;
    private String finishedAt;
    private Error error;

    @Data
    public static class Error {
        private String message;
    }

    @Data
    public static class Attributes {
        private Long branchId;
        private Long directoryId;
        private List<String> targetLanguageIds;
        private boolean skipUntranslatedStrings;
        private boolean skipUntranslatedFiles;
        private boolean exportApprovedOnly;
        private Integer exportWithMinApprovalsCount;
        private boolean exportStringsThatPassedWorkflow;
    }
}
