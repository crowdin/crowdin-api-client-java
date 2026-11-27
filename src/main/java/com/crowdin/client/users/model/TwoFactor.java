package com.crowdin.client.users.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum TwoFactor implements EnumConverter<TwoFactor> {
    ENABLED, DISABLED;

    public static TwoFactor from(String value) {
        return TwoFactor.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(TwoFactor v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
