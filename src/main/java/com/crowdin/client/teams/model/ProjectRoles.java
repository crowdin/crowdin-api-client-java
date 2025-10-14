package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.EnumConverter;

public enum ProjectRoles implements EnumConverter<ProjectRoles> {
    MANAGER, DEVELOPER, TRANSLATOR, PROOFREADER, LANGUAGE_COORDINATOR, MEMBER;

    public static ProjectRoles from(String value) {
        return ProjectRoles.valueOf(value.toUpperCase());
    }

    @Override
    public String to(ProjectRoles v) {
        return v.name().toLowerCase();
    }
}
