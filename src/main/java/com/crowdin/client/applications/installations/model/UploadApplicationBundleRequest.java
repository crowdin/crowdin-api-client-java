package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class UploadApplicationBundleRequest {

    /**
     * Storage identifier of a ZIP archive containing the application bundle
     */
    private Long storageId;
}
