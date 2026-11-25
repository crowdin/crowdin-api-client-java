package com.crowdin.client.users.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Status implements EnumConverter<Status> {
    ACTIVE, PENDING, BLOCKED;

    public static Status from(String value) {
        return Status.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(Status v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
