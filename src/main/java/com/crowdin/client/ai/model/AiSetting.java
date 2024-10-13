package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiSetting {

    private int assistActionAiPromptId;
    private int editorSuggestionAiPromptId;

    @Data
    private static class ShortCut {
        private String name;
        private String prompt;
        private boolean isEnabled;
    }

    private List<ShortCut> shortCuts;
}
