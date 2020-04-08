package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ContentType implements EnumConverter<ContentType> {
    MULTIPART_FORM_DATA("multipart/form-data"), APPLICATION_JSON("application/json"), APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded");

    private final String val;

    ContentType(String val) {
        this.val = val;
    }

    public static ContentType from(String value) {
        for (ContentType type : ContentType.values()) {
            if (type.val.equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public Object to(ContentType v) {
        return v.val;
    }
}
