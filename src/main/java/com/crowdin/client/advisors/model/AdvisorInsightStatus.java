package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorInsightStatus implements EnumConverter<AdvisorInsightStatus> {
    PENDING, CHECKING, OUTDATED, DONE;

    public static AdvisorInsightStatus from(String value) {
        for (AdvisorInsightStatus v : AdvisorInsightStatus.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorInsightStatus value) {
        return value.name().toLowerCase();
    }
}
