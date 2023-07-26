package com.crowdin.client.machinetranslationengines.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {

    GOOGLE, GOOGLE_AUTOML, MICROSOFT, DEEPL, AMAZON, WATSON, MODERNMT, CROWDIN;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Type v) {
        return v.name().toLowerCase();
    }
}
