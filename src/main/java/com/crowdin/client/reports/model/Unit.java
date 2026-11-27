package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Unit implements EnumConverter<Unit> {

    WORDS, STRINGS, CHARS, CHARS_WITH_SPACES, HOURS;

    public static Unit from(String value) {
        return Unit.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(Unit v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
