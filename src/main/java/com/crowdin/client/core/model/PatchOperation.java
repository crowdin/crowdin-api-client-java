package com.crowdin.client.core.model;

import java.util.Locale;

public enum PatchOperation implements EnumConverter<PatchOperation> {
    ADD,
    REMOVE,
    REPLACE,
    MOVE,
    COPY,
    TEST;

    public static PatchOperation from(String value) {
        return PatchOperation.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(PatchOperation value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
