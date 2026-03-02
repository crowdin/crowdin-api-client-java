package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiTranslateRequest {
    private List<String> strings;
    private String sourceLanguageId;
    private String targetLanguageId;
    private List<Long> tmIds;
    private List<Long> glossaryIds;
    private Long aiPromptId;
    private Long aiProviderId;
    private String aiModelId;
    private List<String> instructions;
    private List<Long> attachmentIds;
}
