package com.crowdin.client.users.model;

import com.crowdin.client.core.model.EnumConverter;

public enum OrganizationRoles implements EnumConverter<OrganizationRoles> {
    ADMIN, MANAGER, VENDOR, CLIENT;

    public static OrganizationRoles from(String value){
        return OrganizationRoles.valueOf(value.toUpperCase());
    }

    @Override
    public String to(OrganizationRoles v) {
        return v.name().toLowerCase();
    }
}
