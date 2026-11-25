package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum Method implements EnumConverter<Method> {
    TM, MT, AI;

    public static Method from(String value) {
        return Method.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(Method v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
