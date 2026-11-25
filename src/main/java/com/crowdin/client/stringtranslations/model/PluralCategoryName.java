package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum PluralCategoryName implements EnumConverter<PluralCategoryName> {

    ZERO, ONE, TWO, FEW, MANY, OTHER;

    public static PluralCategoryName from(String value) {
        return PluralCategoryName.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(PluralCategoryName v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
