package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum ExportType implements EnumConverter<ExportType> {
    CONCEPTS, TERMS;

    public static ExportType from(String value) {
        return ExportType.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(ExportType v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
