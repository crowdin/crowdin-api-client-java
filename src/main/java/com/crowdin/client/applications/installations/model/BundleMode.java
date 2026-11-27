package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum BundleMode implements EnumConverter<BundleMode> {

    INTERNAL, EXTERNAL;

    public static BundleMode from(String value) {
        return BundleMode.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(BundleMode value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
