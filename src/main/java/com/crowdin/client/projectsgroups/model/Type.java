package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {
    FILES_BASED(0), STRINGS_BASED(1);

    private final int val;

    Type(int val) {
        this.val = val;
    }

    public static Type from(String value) {
        return value.equals("1") ? Type.STRINGS_BASED : Type.FILES_BASED;
    }

    @Override
    public Integer to(Type v) {
        return v.val;
    }
}
