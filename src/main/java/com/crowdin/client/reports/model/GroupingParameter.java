package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

public enum GroupingParameter implements EnumConverter<GroupingParameter> {
    USER, LANGUAGE;

    public static GroupingParameter from(String value) {
        return GroupingParameter.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(GroupingParameter v) {
        return v.name().toLowerCase();
    }
}
