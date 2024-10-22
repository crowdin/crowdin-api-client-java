package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class Config {
    private List<ActionRulesItem> actionRules;

    @Data
    public static class ActionRulesItem {
        private List<String> availableAiModelIds;
        private String action;
    }
}
