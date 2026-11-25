package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum LabelIncludeType implements EnumConverter<LabelIncludeType> {
    STRINGS_WITH_LABEL,
    STRINGS_WITHOUT_LABEL;

    public static LabelIncludeType from(String value) {
        return LabelIncludeType.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(LabelIncludeType v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
