package com.crowdin.client.workflows.model;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowTemplate {

    private Long id;
    private String title;
    private String description;
    private Long groupId;
    private boolean isDefault;
    private List<Step> steps;
    private String webUrl;

    @Data
    public static class Step {
        private Long id;
        /**
         * Target language identifiers. Typed as strings because the spec documents them as integers while
         * language identifiers are strings elsewhere in the API; numeric values are converted to strings.
         */
        private List<String> languages;
        private List<Long> assignees;
        private Long vendorId;
        private Config config;
        private Long mtId;
    }

    @Data
    public static class Config {
        private Integer minRelevant;
        private Boolean autoSubstitution;
    }
}
