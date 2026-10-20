package com.crowdin.client.users.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LanguagePermission {
    private Object workflowStepIds;
    /**
     * Crowdin only. In Crowdin responses a language permission is a plain role string
     * (e.g. {@code translator}, {@code proofreader}, {@code denied}) instead of an object.
     * Read-only, never sent in requests.
     */
    @JsonIgnore
    private String role;

    /**
     * Creates a language permission from the Crowdin role string representation
     *
     * @param role role name
     * @return language permission
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static LanguagePermission fromRole(String role) {
        LanguagePermission permission = new LanguagePermission();
        permission.setRole(role);
        return permission;
    }
}
