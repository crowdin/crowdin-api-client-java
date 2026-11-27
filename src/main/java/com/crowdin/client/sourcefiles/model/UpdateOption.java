package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum UpdateOption implements EnumConverter<UpdateOption> {
    CLEAR_TRANSLATIONS_AND_APPROVALS, KEEP_TRANSLATIONS, KEEP_TRANSLATIONS_AND_APPROVALS;

    public static UpdateOption from(String value) {
        return UpdateOption.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(UpdateOption v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
