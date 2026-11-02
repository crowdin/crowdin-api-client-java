package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Defines how labels are matched: strings must have all of the specified labels ({@link #ALL})
 * or at least one of them ({@link #ANY}).
 */
public enum LabelMatchRule implements EnumConverter<LabelMatchRule> {
    ALL, ANY;

    public static LabelMatchRule from(String value) {
        for (LabelMatchRule rule : LabelMatchRule.values()) {
            if (rule.name().equalsIgnoreCase(value)) {
                return rule;
            }
        }
        return null;
    }

    @Override
    public String to(LabelMatchRule v) {
        return v.name().toLowerCase();
    }
}
