package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class Permissions {

    /**
     * @deprecated deprecated by the API, use per-module permissions ({@link ModulePermissions}) instead:
     * {@code modules[].permissions} of {@link InstallApplicationRequest} and {@link Module}
     */
    @Deprecated
    private User user;
    private Project project;

}
