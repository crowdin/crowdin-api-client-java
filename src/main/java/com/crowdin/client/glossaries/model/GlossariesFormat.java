package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

public enum GlossariesFormat implements EnumConverter<GlossariesFormat> {
    TBX, TBX_V3, CSV, XLSX;

    public static GlossariesFormat from(String value) {
        return GlossariesFormat.valueOf(value.toUpperCase());
    }

    @Override
    public String to(GlossariesFormat v) {
        return v.name().toLowerCase();
    }
}