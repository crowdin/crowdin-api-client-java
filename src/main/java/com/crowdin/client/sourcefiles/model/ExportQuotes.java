package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ExportQuotes implements EnumConverter<ExportQuotes> {

    SINGLE, DOUBLE;

    public static ExportQuotes from(String value) {
        return ExportQuotes.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ExportQuotes v) {
        return v.name().toLowerCase();
    }
}
