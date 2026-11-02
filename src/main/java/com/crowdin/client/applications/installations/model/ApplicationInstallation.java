package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ApplicationInstallation {

    private String identifier;
    private String name;
    private InstallationUser installedBy;
    private String description;
    private String logo;
    private String logoUrl;
    private InstallationUser agent;
    private String baseUrl;
    private String manifestUrl;
    /**
     * The manifest of the installation as authored. {@code null} for applications installed from a manifest URL
     */
    private Map<String, Object> manifest;
    private Date createdAt;
    private Date manifestUpdatedAt;
    private Boolean isManifestOutdated;
    private Module[] modules;
    private String[] scopes;
    private Permissions permissions;
    private Object defaultPermissions;
    private boolean limitReached;
    private Boolean stringBasedAvailable;
    private ApplicationBundle bundle;

}
