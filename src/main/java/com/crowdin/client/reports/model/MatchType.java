package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

public enum MatchType implements EnumConverter<MatchType> {
    PERFECT("perfect"),
    OPTION_100("100"),
    OPTION_99_82("99-82"),
    OPTION_81_60("81-60");

    private String value;

    MatchType(String value) {
        this.value = value;
    }

    public static MatchType from(String value) {
        for (MatchType val : MatchType.values()) {
            if (val.value.equals(value)) {
                return val;
            }
        }
        return null;
    }

    @Override
    public Object to(MatchType v) {
        return v.value;
    }
}
