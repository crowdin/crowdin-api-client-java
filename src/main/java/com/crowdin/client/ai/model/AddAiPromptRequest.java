package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AddAiPromptRequest {
    private String name;
    private String action;
    private long aiProviderId;
    private String aiModelId;
    private boolean isEnabled;
    private List<Long> enabledProjectIds;
    private Config config;

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
