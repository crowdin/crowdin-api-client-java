package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Format implements EnumConverter<Format> {

    TBX, CSV, XLSX;

    public static Format from(String value) {
        return Format.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Format v) {
        return v.name().toLowerCase();
    }
}
