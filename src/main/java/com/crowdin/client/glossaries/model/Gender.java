package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Gender implements EnumConverter<Gender> {
    MASCULINE, FEMININE, NEUTER, OTHER;

    public static Gender from(String value) {
        return Gender.valueOf(value.toUpperCase(Locale.ROOT).replaceAll(" ", "_"));
    }

    @Override
    public String to(Gender v) {
        return v.name().toLowerCase(Locale.ROOT).replaceAll("_", " ");
    }
}
