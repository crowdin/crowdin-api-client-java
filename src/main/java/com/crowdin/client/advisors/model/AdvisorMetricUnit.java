package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorMetricUnit implements EnumConverter<AdvisorMetricUnit> {
    PERCENT, COUNT;

    public static AdvisorMetricUnit from(String value) {
        for (AdvisorMetricUnit v : AdvisorMetricUnit.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorMetricUnit value) {
        return value.name().toLowerCase();
    }
}
