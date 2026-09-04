package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.EnumSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListAiRequestLogsParams extends Pagination {
    private String requestId;
    private Long projectId;
    private Long userId;
    private Long aiProviderId;
    private String model;
    private AiRequestSourceAction sourceAction;
    private String promptAction;
    private EnumSet<AiRequestLogStatus> statuses;
    private Boolean systemCredentials;
    private Boolean isAutoTriggered;
    private String tokenName;
    private String oauthClientId;
    private String createdAfter;
    private String createdBefore;
}
