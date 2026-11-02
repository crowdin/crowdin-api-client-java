package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Inline manifest content used to install a serverless application (an app with no backend).
 */
@Data
public class ApplicationManifest {

    private String name;
    private String description;
    private String logo;
    private List<String> scopes;
    private Boolean stringBasedAvailable;
    /**
     * UI module definitions: keys are UI module types, values are arrays of module definitions
     */
    private Map<String, List<ApplicationManifestModule>> modules;
    private DefaultPermissions default_permissions;
    private ApplicationBundle bundle;
}
