package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum RequestType implements EnumConverter<RequestType> {
    POST, GET;

    public static RequestType from(String value) {
        return RequestType.valueOf(value);
    }

    @Override
    public Object to(RequestType v) {
        return v.name();
    }
}
