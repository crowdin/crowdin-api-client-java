package com.crowdin.client.core.model;

public enum PatchOperation implements EnumConverter<PatchOperation> {
    ADD,
    REMOVE,
    REPLACE,
    MOVE,
    COPY,
    TEST;

    public static PatchOperation from(String value) {
        return PatchOperation.valueOf(value.toUpperCase());
    }

    @Override
    public String to(PatchOperation value) {
        return value.name().toLowerCase();
    }
}
