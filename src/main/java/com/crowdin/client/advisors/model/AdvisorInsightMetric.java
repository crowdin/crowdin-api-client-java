package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdvisorInsightMetric {

    private String key;
    private Double value;
    private AdvisorMetricUnit unit;
    private Double threshold;
    private AdvisorMetricTone tone;
    private AdvisorMetricSource source;
    private Date checkedAt;
}
