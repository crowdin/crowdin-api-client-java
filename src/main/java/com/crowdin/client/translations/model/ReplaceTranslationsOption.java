package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ReplaceTranslationsOption implements EnumConverter<ReplaceTranslationsOption> {
    NONE("none"), AUTO_TRANSLATED("autoTranslated"), ALL("all");

    private final String value;

    ReplaceTranslationsOption(String value) {
        this.value = value;
    }

    public static ReplaceTranslationsOption from(String value) {
        for (ReplaceTranslationsOption o : ReplaceTranslationsOption.values()) {
            if (o.value.equals(value)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public String to(ReplaceTranslationsOption v) {
        return v.value;
    }
}
