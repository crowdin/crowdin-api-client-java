package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Scope implements EnumConverter<Scope> {
    UNTRANSLATED, TRANSLATED, ALL;

    public static Scope from(String value) {
        return Scope.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Scope v) {
        return v.name().toLowerCase();
    }
}
