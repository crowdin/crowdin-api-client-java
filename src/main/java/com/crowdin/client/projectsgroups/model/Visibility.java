package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Visibility implements EnumConverter<Visibility> {
    OPEN, PRIVATE;

    public static Visibility from(String value) {
        return Visibility.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(Visibility v) {
        return v.name().toLowerCase();
    }
}
