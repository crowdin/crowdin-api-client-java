package com.crowdin.client.fields.model.enums;

import com.crowdin.client.core.model.EnumConverter;

public enum EntityType implements EnumConverter<EntityType> {

    PROJECT,
    USER,
    TASK,
    FILE,
    TRANSLATION,
    STRING;

    public static EntityType from(String value) {
        return EntityType.valueOf(value.toUpperCase());
    };

    @Override
    public Object to(EntityType v) {
        return v.name().toLowerCase();
    }
}
