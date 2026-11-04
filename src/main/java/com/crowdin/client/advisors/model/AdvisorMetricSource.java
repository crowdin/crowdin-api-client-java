package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorMetricSource implements EnumConverter<AdvisorMetricSource> {
    DETERMINISTIC, AI, APP;

    public static AdvisorMetricSource from(String value) {
        for (AdvisorMetricSource v : AdvisorMetricSource.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorMetricSource value) {
        return value.name().toLowerCase();
    }
}
