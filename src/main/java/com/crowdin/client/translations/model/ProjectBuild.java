package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectBuild {

    private Long id;
    private String status;
    private Integer progress;
    private List<Attributes> attributes;

    @Data
    public static class Attributes {
        private Long projectId;
        private Long branchId;
        private List<String> targetLanguageIds;
        private boolean exportTranslatedOnly;
        private boolean skipUntranslatedFiles;
        private boolean exportApprovedOnly;
    }
}
