package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class ApplyApplicationInstallationUpdateRequest {

    /**
     * Hash of the reviewed manifest version (from the Get Application Installation Update response)
     */
    private String manifestHash;
}
