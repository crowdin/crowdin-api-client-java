package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationManifestModule {

    private String key;
    private String name;
    private String logo;
    private String description;
    /**
     * Crowdin editions the module is enabled in: {@code crowdin}, {@code crowdin-enterprise}
     */
    private List<String> environments;
    private ModulePermissions permissions;
}
