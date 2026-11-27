package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum GroupingParameter implements EnumConverter<GroupingParameter> {
    USER, LANGUAGE, PROJECT;

    public static GroupingParameter from(String value) {
        return GroupingParameter.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public Object to(GroupingParameter v) {
        return v.name().toLowerCase(Locale.ROOT);
    }
}
