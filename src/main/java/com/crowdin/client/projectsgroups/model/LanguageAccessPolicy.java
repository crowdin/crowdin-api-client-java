package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EnumConverter;

public enum LanguageAccessPolicy implements EnumConverter<LanguageAccessPolicy> {
    OPEN, MODERATE;

    public static LanguageAccessPolicy from(String value) {
        return LanguageAccessPolicy.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(LanguageAccessPolicy v) {
        return v.name().toLowerCase();
    }
}
