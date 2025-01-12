package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AiPromptCompletionRequest {
    private AiPromptResource resources;
    private Map<String, Object> tools;
    private Map<String, Object> tool_choice;

    @Data
    public static class AiPromptResource {
        private Long projectId;
        private String sourceLanguageId;
        private String targetLanguageId;
        private List<Long> stringIds;
        private List<Long> filteredStringsIds;
        private Map<String, Object> overridePromptValues;
        private String customInstruction;
    }
}
