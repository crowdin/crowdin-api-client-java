package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorInsightOutcome implements EnumConverter<AdvisorInsightOutcome> {
    FLAGGED, CLEAR, NOT_APPLICABLE;

    public static AdvisorInsightOutcome from(String value) {
        for (AdvisorInsightOutcome v : AdvisorInsightOutcome.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorInsightOutcome value) {
        return value.name().toLowerCase();
    }
}
