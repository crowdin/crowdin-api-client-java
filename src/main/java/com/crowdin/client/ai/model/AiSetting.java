package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiSetting {

    private Long assistActionAiPromptId;
    private Boolean showSuggestion;
    private Long editorSuggestionAiPromptId;

    @Data
    private static class ShortCut {
        private String name;
        private String prompt;
        private Boolean isEnabled;
    }

    private List<ShortCut> shortcuts;
}
