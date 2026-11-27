package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum UserPermissions implements EnumConverter<UserPermissions> {

    OWNER, MANAGERS, ALL, GUESTS, RESTRICTED;

    public static UserPermissions from(String value) {
        return UserPermissions.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(UserPermissions value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
