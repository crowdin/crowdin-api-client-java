package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum BundleMode implements EnumConverter<BundleMode> {

    INTERNAL, EXTERNAL;

    public static BundleMode from(String value) {
        return BundleMode.valueOf(value.toUpperCase());
    }

    @Override
    public String to(BundleMode value) {
        return value.name().toLowerCase();
    }
}
