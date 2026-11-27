package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

import java.util.Locale;

public enum ProjectPermissions implements EnumConverter<ProjectPermissions> {

    OWN, RESTRICTED;

    public static ProjectPermissions from(String value) {
        return ProjectPermissions.valueOf(value.toUpperCase(Locale.ROOT));
    }

    @Override
    public String to(ProjectPermissions value) {
        return value.name().toLowerCase(Locale.ROOT);
    }
}
