package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Method implements EnumConverter<Method> {
    TM, MT, AI;

    public static Method from(String value) {
        return Method.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Method v) {
        return v.name().toLowerCase();
    }
}
