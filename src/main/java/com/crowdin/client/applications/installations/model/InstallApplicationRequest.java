package com.crowdin.client.applications.installations.model;

import lombok.Data;

import java.util.List;

/**
 * Install an application either from a manifest URL ({@code url}) or from inline manifest content ({@code manifest},
 * serverless apps only). Set exactly one of {@code url} or {@code manifest}.
 */
@Data
public class InstallApplicationRequest {

    /**
     * Manifest URL (install from a manifest URL)
     */
    private String url;
    /**
     * Inline manifest content (install from manifest content, serverless apps only)
     */
    private ApplicationManifest manifest;
    private Permissions permissions;
    private List<InstallApplicationModule> modules;
    /**
     * Assign Agent as a manager to all existing projects (install from a manifest URL only)
     */
    private Boolean assignAgent;
    /**
     * Hash of the manifest as it was loaded for review (install from a manifest URL only)
     */
    private String manifestHash;
}
