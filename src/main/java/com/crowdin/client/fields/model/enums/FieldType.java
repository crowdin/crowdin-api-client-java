package com.crowdin.client.fields.model.enums;

import com.crowdin.client.core.model.EnumConverter;

public enum FieldType implements EnumConverter<FieldType> {

    CHECKBOX,
    RADIOBUTTONS,
    DATE,
    DATETIME,
    NUMBER,
    LABELS,
    SELECT,
    MULTISELECT,
    TEXT,
    TEXTAREA,
    URL;

    public static FieldType from(String value) {
        return FieldType.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(FieldType v) {
        return v.name().toLowerCase();
    }
}