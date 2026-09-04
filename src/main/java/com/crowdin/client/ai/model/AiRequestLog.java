package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiRequestLog {
    private Long id;
    private String requestId;
    private Date createdAt;
    private AiRequestLogStatus status;
    private Integer httpStatus;
    private String model;
    private AiRequestSourceAction sourceAction;
    private String promptAction;
    private Boolean systemCredentials;
    private Boolean isAutoTriggered;
    private Integer durationMs;
    private Integer inputTokens;
    private Integer outputTokens;
    private Double totalCost;
    private Long userId;
    private Long projectId;
    private Long promptId;
    private Long aiProviderId;
    private String tokenName;
    private String oauthClientId;
    private String oauthClientName;
    private String ip;
    private String userAgent;
    private String error;
}
