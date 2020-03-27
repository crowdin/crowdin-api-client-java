package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum PluralCategoryName implements EnumConverter<PluralCategoryName> {

    ZERO, ONE, TWO, FEW, MANY, OTHER;

    public static PluralCategoryName from(String value) {
        return PluralCategoryName.valueOf(value.toUpperCase());
    }

    @Override
    public String to(PluralCategoryName v) {
        return v.name().toLowerCase();
    }
}
