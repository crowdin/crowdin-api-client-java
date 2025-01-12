package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AiPromptCompletionResponse {
    private AiPromptCompletionData data;

    @Data
    public static class AiPromptCompletionData {
        private String identifier;
        private String status;
        private Long progress;
        private CompletionAttributes attributes;
        private Date createdAt;
        private Date updatedAt;
        private Date startedAt;
    }

    @Data
    public static class CompletionAttributes {
        private Long aiPromptId;
    }
}
