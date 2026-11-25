package com.crowdin.client.core.model;

import java.util.Locale;

public enum SortOrder implements EnumConverter<SortOrder> {
    ASC, DESC;

    public static SortOrder from(String value) {
        if (value == null) return ASC;

        return SortOrder.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(SortOrder v) {
        return (v != null ? v : ASC).name().toLowerCase(Locale.ROOT);
    }
}
