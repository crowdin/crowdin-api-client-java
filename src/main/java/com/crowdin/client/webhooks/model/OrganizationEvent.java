package com.crowdin.client.webhooks.model;

import com.crowdin.client.core.model.EnumConverter;

public enum OrganizationEvent implements EnumConverter<OrganizationEvent> {
    GROUP_CREATED, GROUP_DELETED,
    PROJECT_CREATED, PROJECT_DELETED;

    public static OrganizationEvent from(String value) {
        return OrganizationEvent.valueOf(value.toUpperCase().replace(".", "_"));
    }

    @Override
    public Object to(OrganizationEvent v) {
        return v.name().toLowerCase().replace("_", ".");
    }
}
