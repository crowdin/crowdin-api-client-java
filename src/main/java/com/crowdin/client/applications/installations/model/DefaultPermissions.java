
package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class DefaultPermissions {

    private UserPermissions user = UserPermissions.from("owner");
    private ProjectPermissions project = ProjectPermissions.from("own");

}
