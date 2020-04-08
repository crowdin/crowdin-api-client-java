package com.crowdin.client.users.model;

import com.crowdin.client.core.model.EnumConverter;

public enum TwoFactor implements EnumConverter<TwoFactor> {
    ENABLED, DISABLED;

    public static TwoFactor from(String value) {
        return TwoFactor.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(TwoFactor v) {
        return v.name().toLowerCase();
    }
}
