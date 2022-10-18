package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Status implements EnumConverter<Status> {
    PREFERRED, ADMITTED, NOT_RECOMMENDED, OBSOLETE;

    public static Status from(String value) {
        return Status.valueOf(value.toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public String to(Status v) {
        return v.name().toLowerCase().replaceAll("_", " ");
    }
}
