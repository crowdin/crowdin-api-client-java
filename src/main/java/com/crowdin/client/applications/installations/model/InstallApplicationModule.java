package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class InstallApplicationModule {

    private String key;
    private ModulePermissions permissions;
}
