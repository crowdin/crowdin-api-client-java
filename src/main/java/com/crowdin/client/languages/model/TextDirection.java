package com.crowdin.client.languages.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum TextDirection implements EnumConverter<TextDirection> {

    LTR, RTL;

    public static TextDirection from(String value) {
        return TextDirection.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(TextDirection v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
