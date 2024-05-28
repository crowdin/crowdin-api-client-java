package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AddAiProviderRequest {
    private String name;
    private String type;
    private Credentials credentials;
    private Config config;
    private boolean isEnabled;
    private boolean useSystemCredentials;

    @Data
    private static class Credentials {
        private String apiKey;
    }

    @Data
    private static class Config {
        private List<Config.ActionRule> actionRules;

        @Data
        private static class ActionRule {
            private String action;
            private List<String> availableAiModelIds;
        }
    }
}
