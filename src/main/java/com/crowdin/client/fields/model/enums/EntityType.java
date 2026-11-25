package com.crowdin.client.fields.model.enums;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum EntityType implements EnumConverter<EntityType> {

    PROJECT,
    USER,
    TASK,
    FILE,
    TRANSLATION,
    STRING,
    TERM,
    CONCEPT;

    public static EntityType from(String value) {
        return EntityType.valueOf(value.toUpperCase(Locale.ROOT));
    };

    @Override
    public Object to(EntityType v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
