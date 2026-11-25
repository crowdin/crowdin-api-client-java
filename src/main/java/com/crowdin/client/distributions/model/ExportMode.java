package com.crowdin.client.distributions.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum ExportMode implements EnumConverter<ExportMode> {

    DEFAULT, BUNDLE;

    public static ExportMode from(String value) {
        return ExportMode.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(ExportMode v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
