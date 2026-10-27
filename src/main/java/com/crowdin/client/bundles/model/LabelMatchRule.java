package com.crowdin.client.bundles.model;

import com.crowdin.client.core.model.EnumConverter;

public enum LabelMatchRule implements EnumConverter<LabelMatchRule> {

    ALL, ANY;

    public static LabelMatchRule from(String value) {
        return LabelMatchRule.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(LabelMatchRule v) {
        return v.name().toLowerCase();
    }
}
