package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorRefreshPolicy implements EnumConverter<AdvisorRefreshPolicy> {
    HOURLY, DAILY;

    public static AdvisorRefreshPolicy from(String value) {
        for (AdvisorRefreshPolicy v : AdvisorRefreshPolicy.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorRefreshPolicy value) {
        return value.name().toLowerCase();
    }
}
