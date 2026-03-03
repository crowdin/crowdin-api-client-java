package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiFileTranslationAddRequest {
    private Long storageId;
    private String sourceLanguageId;
    private String targetLanguageId;
    private String type;
    private Integer parserVersion;
    private List<Long> tmIds;
    private List<Long> glossaryIds;
    private Long aiPromptId;
    private Long aiProviderId;
    private String aiModelId;
    private List<String> instructions;
    private List<Long> attachmentIds;
}
