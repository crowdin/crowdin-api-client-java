package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Status implements EnumConverter<Status> {
    TODO, IN_PROGRESS, DONE, CLOSED;

    public static Status from(String value) {
        return Status.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Status v) {
        return v.name().toLowerCase();
    }
}
