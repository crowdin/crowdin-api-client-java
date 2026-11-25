package com.crowdin.client.stringcomments.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Type implements EnumConverter<Type> {
    COMMENT, ISSUE;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(Type v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
