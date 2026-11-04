package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AdvisorAiMode implements EnumConverter<AdvisorAiMode> {
    AUTO, ALL;

    public static AdvisorAiMode from(String value) {
        for (AdvisorAiMode v : AdvisorAiMode.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(AdvisorAiMode value) {
        return value.name().toLowerCase();
    }
}
