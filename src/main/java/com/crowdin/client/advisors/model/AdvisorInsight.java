package com.crowdin.client.advisors.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AdvisorInsight {

    private Long id;
    private String inspectorKey;
    private String category;
    private Boolean isDismissed;
    private AdvisorInsightStatus status;
    private AdvisorInsightOutcome outcome;
    private AdvisorInsightSeverity severity;
    private AdvisorRefreshPolicy refreshPolicy;
    private List<AdvisorInsightMetric> metrics;
    private List<AdvisorInsightRecommendation> recommendations;
    private Date checkedAt;
    private AdvisorAiRun lastAiRun;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, Object> payload;
}
