package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ProjectPermissions implements EnumConverter<ProjectPermissions> {

    OWN, RESTRICTED;

    public static ProjectPermissions from(String value) {
        return ProjectPermissions.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ProjectPermissions value) {
        return value.name().toLowerCase();
    }
}
