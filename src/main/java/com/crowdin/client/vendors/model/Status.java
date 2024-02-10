package com.crowdin.client.vendors.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Status implements EnumConverter<Status> {
    PENDING, CONFIRMED, REJECTED;

    public static Status from(String value) {
        return Status.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Status v) {
        return v.name().toLowerCase();
    }
}
