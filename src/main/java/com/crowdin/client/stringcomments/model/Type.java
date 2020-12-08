package com.crowdin.client.stringcomments.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {
    COMMENT, ISSUE;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(Type v) {
        return v.name().toLowerCase();
    }
}
