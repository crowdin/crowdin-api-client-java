package com.crowdin.client.core.model;

public enum JsonFileType implements EnumConverter<JsonFileType> {
    I18NEXT_JSON("i18next_json"),
    NESTJS_I18N("nestjs_i18n");

    private String value;

    JsonFileType(String value) {
        this.value = value;
    }

    public static JsonFileType from(String value) {
        return JsonFileType.valueOf(value.toUpperCase());
    }

    @Override
    public String to(JsonFileType v) {
        return v.value;
    }
}
