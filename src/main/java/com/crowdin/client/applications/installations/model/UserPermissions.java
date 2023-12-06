package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum UserPermissions implements EnumConverter<UserPermissions> {

    OWNER, MANAGERS, ALL, GUESTS, RESTRICTED;

    public static UserPermissions from(String value) {
        return UserPermissions.valueOf(value.toUpperCase());
    }

    @Override
    public String to(UserPermissions value) {
        return value.name().toLowerCase();
    }
}
