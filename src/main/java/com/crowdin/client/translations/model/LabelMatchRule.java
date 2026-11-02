package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * How a string is matched against a list of labels.
 */
public enum LabelMatchRule implements EnumConverter<LabelMatchRule> {
    ALL("all"), ANY("any");

    private final String value;

    LabelMatchRule(String value) {
        this.value = value;
    }

    public static LabelMatchRule from(String value) {
        for (LabelMatchRule r : LabelMatchRule.values()) {
            if (r.value.equals(value)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String to(LabelMatchRule v) {
        return v.value;
    }
}
