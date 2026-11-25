package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum ExportQuotes implements EnumConverter<ExportQuotes> {

    SINGLE, DOUBLE;

    @JsonCreator
    public static ExportQuotes from(String value) {
        return ExportQuotes.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @JsonValue
    @Override
    public String to(ExportQuotes v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
