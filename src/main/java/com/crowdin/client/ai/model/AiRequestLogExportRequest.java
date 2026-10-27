package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;

@Data
public class AiRequestLogExportRequest {
    private AiRequestLogExportFormat format;
    private String requestId;
    private Long projectId;
    private Long userId;
    private Long aiProviderId;
    private String model;
    private AiRequestSourceAction sourceAction;
    private String promptAction;
    private List<AiRequestLogStatus> statuses;
    private String tokenName;
    private String oauthClientId;
    private Boolean systemCredentials;
    private Boolean isAutoTriggered;
    private String createdAfter;
    private String createdBefore;
}
