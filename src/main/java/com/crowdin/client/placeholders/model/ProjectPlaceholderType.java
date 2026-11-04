package com.crowdin.client.placeholders.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ProjectPlaceholderType implements EnumConverter<ProjectPlaceholderType> {
    HIGH, LOW;

    public static ProjectPlaceholderType from(String value) {
        for (ProjectPlaceholderType v : ProjectPlaceholderType.values()) {
            if (v.name().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String to(ProjectPlaceholderType value) {
        return value.name().toLowerCase();
    }
}
