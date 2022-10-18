package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {
    FULL_FORM, ACRONYM, ABBREVIATION, SHORT_FORM, PHRASE, VARIANT;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase().replaceAll(" ", "_"));
    }

    @Override
    public String to(Type v) {
        return v.name().toLowerCase().replaceAll("_", " ");
    }
}
