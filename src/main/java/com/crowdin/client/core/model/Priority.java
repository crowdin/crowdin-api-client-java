package com.crowdin.client.core.model;

public enum Priority implements EnumConverter<Priority> {
    LOW, NORMAL, HIGH;

    public static Priority from(String value) {
        return Priority.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Priority v) {
        return v.name().toLowerCase();
    }
}
