package com.crowdin.client.issues.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {
    ALL, GENERAL_QUESTION, TRANSLATION_MISTAKE, CONTEXT_REQUEST, SOURCE_MISTAKE;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(Type v) {
        return v.name().toLowerCase();
    }
}
