package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public abstract class WorkflowTemplateStepConfig {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigTranslateProofread extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        private List<Integer> assignees;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigVendor extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        private Integer vendorId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigTMPreTranslate extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        private Config config;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigMTPreTranslate extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        private Integer mtId;
    }

    @Data
    public static class Config {
        private Integer minRelevant;
        private Boolean autoSubstitution;
    }
}
