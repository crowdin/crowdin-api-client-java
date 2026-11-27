package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Reports2Format implements EnumConverter<Reports2Format> {

    XLSX, CSV;

    public static Reports2Format from(String value) {
        return Reports2Format.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(Reports2Format v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}