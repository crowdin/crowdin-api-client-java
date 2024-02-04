package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationInstallation {

    private String identifier;
    private String name;
    private String description;
    private String logo;
    private String baseUrl;
    private String manifestUrl;
    private Date createdAt;
    private Module[] modules;
    private String[] scopes;
    private Permissions permissions;
    private DefaultPermissions defaultPermissions;
    private boolean limitReached;

}
