package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.Map;

@Data
public class AdvisorInsightRecommendation {

    private String id;
    private Boolean primary;
    private Map<String, Object> params;
}
