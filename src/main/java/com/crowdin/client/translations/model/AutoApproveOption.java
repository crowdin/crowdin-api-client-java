package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AutoApproveOption implements EnumConverter<AutoApproveOption> {
    NONE("node"), ALL("all"), EXCEPT_AUTO_SUBSTITUTED("exceptAutoSubstituted"), PERFECT_MATCH_ONLY("perfectMatchOnly");

    private final String value;

    AutoApproveOption(String value) {
        this.value = value;
    }

    public static AutoApproveOption from(String value) {
        for (AutoApproveOption m : AutoApproveOption.values()) {
            if (m.value.equals(value)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String to(AutoApproveOption v) {
        return v.value;
    }
}
