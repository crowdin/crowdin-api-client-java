package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

public abstract class WorkflowTemplateStepConfig {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigTranslateProofread extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        /**
         * @deprecated deprecated in the API, use {@link TranslateProofreadConfig#assignees} in {@link #config} instead
         */
        @Deprecated
        private List<Integer> assignees;
        private TranslateProofreadConfig config;
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
    @EqualsAndHashCode(callSuper = true)
    public static class WorkflowTemplateStepConfigAIPreTranslate extends WorkflowTemplateStepConfig {
        private Integer id;
        private List<String> languages;
        private Long promptId;
    }

    @Data
    public static class Config {
        private Integer minRelevant;
        private Boolean autoSubstitution;
        /**
         * One of: all, perfectMatchOnly, exceptAutoSubstituted, perfectMatchApprovedOnly, none
         */
        private String autoApproveOption;
    }

    /**
     * Use only with Translation and Proofreading step types
     */
    @Data
    public static class TranslateProofreadConfig {
        /**
         * Keys are language identifiers, values are user identifiers
         */
        private Map<String, List<Long>> assignees;
        /**
         * Keys are language identifiers, values are team identifiers
         */
        private Map<String, List<Long>> assignedTeams;
    }
}
