package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class AiProviderModel {
    private String id;
    private String provider;
    private String providerName;
    private Long providerId;
    private Long contextWindow;
    private Long maxOutputTokens;
    private Boolean supportsStreaming;
    private Boolean supportsFunctionCalling;
    private Boolean supportsJsonMode;
    private Boolean supportsJsonSchema;
    private Boolean supportsVision;
    private Boolean isCompatibleWithAiLimit;
}
