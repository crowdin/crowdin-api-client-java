package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class UpsertAdvisorInsightRequest {

    private AdvisorInsightOutcome outcome;
    private Date checkedAt;
    private List<AdvisorInsightMetric> metrics;
    private List<AdvisorInsightRecommendation> recommendations;
    private Map<String, Object> payload;
}
