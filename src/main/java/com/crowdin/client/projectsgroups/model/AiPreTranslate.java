package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class AiPreTranslate {

    private Boolean enabled;
    private List<AiPromptItem> aiPrompts;

    @Data
    public static class AiPromptItem {
        private Long aiPromptId;
        private List<String> languageIds;
    }
}
