package com.crowdin.client.users.model;

import com.crowdin.client.core.model.EnumConverter;

public enum TranslatorRoleName implements EnumConverter<TranslatorRoleName> {
    TRANSLATOR, PROOFREADER;

    public static TranslatorRoleName from(String value){
        return TranslatorRoleName.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(TranslatorRoleName v) {
        return v.name().toLowerCase();
    }
}
