package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorInsightSeverity implements EnumConverter<AdvisorInsightSeverity> {
    HIGH, MEDIUM, LOW;

    public static AdvisorInsightSeverity from(String value) {
        for (AdvisorInsightSeverity v : AdvisorInsightSeverity.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorInsightSeverity value) {
        return value.name().toLowerCase();
    }
}
