package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AiPrompt {
    private Long id;
    private String name;
    private String action;
    private Long aiProviderId;
    private String aiModelId;
    private Boolean isEnabled;
    private List<Long> enabledProjectIds;
    private Map<String, Object> config;
    private String promptPreview;
    private Boolean isFineTuningAvailable;
    private Date createdAt;
    private Date updatedAt;
}
