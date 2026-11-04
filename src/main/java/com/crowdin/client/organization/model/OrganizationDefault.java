package com.crowdin.client.organization.model;

import lombok.Data;

@Data
public class OrganizationDefault {

    private String name;
    private Boolean isLocked;
    private String defaultValue;
}
