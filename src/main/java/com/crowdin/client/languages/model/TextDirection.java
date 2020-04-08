package com.crowdin.client.languages.model;

import com.crowdin.client.core.model.EnumConverter;

public enum TextDirection implements EnumConverter<TextDirection> {

    LTR, RTL;

    public static TextDirection from(String value) {
        return TextDirection.valueOf(value.toUpperCase());
    }

    @Override
    public String to(TextDirection v) {
        return v.name().toLowerCase();
    }
}
