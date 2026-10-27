package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AiPromptAddRequest {
    private String name;
    private String action;
    private Long aiProviderId;
    private String aiModelId;
    /**
     * @deprecated deprecated in the API
     */
    @Deprecated
    private Boolean isEnabled;
    private List<Long> enabledProjectIds;
    private Map<String, Object> config;
}
