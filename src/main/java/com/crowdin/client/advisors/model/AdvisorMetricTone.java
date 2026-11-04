package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorMetricTone implements EnumConverter<AdvisorMetricTone> {
    DEFAULT, SUCCESS, DANGER;

    public static AdvisorMetricTone from(String value) {
        for (AdvisorMetricTone v : AdvisorMetricTone.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorMetricTone value) {
        return value.name().toLowerCase();
    }
}
