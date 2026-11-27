package com.crowdin.client.applications.consents.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum ConsentStatus implements EnumConverter<ConsentStatus> {
    GRANTED, DENIED;

    public static ConsentStatus from(String value) {
        return ConsentStatus.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(ConsentStatus v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
