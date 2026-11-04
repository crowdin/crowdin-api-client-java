package com.crowdin.client.advisors.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
public class AdvisorInsightRecommendation {

    private String id;
    private Boolean primary;
    @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
    private Map<String, Object> params;
}
