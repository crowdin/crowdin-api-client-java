package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Mark implements EnumConverter<Mark> {
    UP, DOWN;

    public static Mark from(String value) {
        return Mark.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Mark v) {
        return v.name().toLowerCase();
    }
}
