package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Unit implements EnumConverter<Unit> {

    WORDS, STRINGS, CHARS, CHARS_WITH_SPACES;

    public static Unit from(String value) {
        return Unit.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Unit v) {
        return v.name().toLowerCase();
    }
}
