package com.crowdin.client.core.model;

public enum SortOrder implements EnumConverter<SortOrder> {
    ASC, DESC;

    public static SortOrder from(String value) {
        if (value == null) return ASC;

        return SortOrder.valueOf(value.toUpperCase());
    }

    @Override
    public String to(SortOrder v) {
        return (v != null ? v : ASC).name().toLowerCase();
    }
}
