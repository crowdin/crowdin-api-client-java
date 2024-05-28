package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiPrompt {
    private long id;
    private String name;
    private String action;
    private long aiProviderId;
    private String aiModelId;
    private boolean isEnabled;
    private List<Long> enabledProjectIds;
    private Config config;
    private String createdAt;
    private String updatedAt;

    @Data
    private static class Config {
        private String mode;
        private String companyDescription;
        private String projectDescription;
        private String audienceDescription;
        private OtherLanguageTranslations otherLanguageTranslations;
        private boolean glossaryTerms;
        private boolean tmSuggestions;
        private boolean fileContent;
        private boolean fileContext;
        private boolean publicProjectDescription;

        @Data
        private static class OtherLanguageTranslations {
            private boolean isEnabled;
            private List<String> languageIds;
        }
    }
}
