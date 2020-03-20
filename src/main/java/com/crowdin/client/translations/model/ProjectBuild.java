package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectBuild {

    private Long id;
    private String status;
    private Integer progress;
    private List<Attribute> attributes;

    @Data
    public static class Attribute {
        private Long projectId;
        private Long branchId;
        private List<String> targetLanguagesId;
        private boolean exportTranslatedOnly;
        private boolean skipUntranslatedFiles;
        private boolean exportApprovedOnly;
    }
}
