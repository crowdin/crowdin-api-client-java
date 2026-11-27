package com.crowdin.client.core.model;

import java.util.Locale;

public enum Priority implements EnumConverter<Priority> {
    LOW, NORMAL, HIGH;

    public static Priority from(String value) {
        return Priority.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(Priority v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
