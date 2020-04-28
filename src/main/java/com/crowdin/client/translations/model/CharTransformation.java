package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum CharTransformation implements EnumConverter<CharTransformation> {
    ASIAN, CYRILLIC, EUROPEAN, ARABIC;

    public static CharTransformation from(String value) {
        return CharTransformation.valueOf(value.toUpperCase());
    }

    @Override
    public String to(CharTransformation v) {
        return v.name().toLowerCase();
    }
}
