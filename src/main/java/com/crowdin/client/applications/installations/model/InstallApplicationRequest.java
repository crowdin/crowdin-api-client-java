package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class InstallApplicationRequest {

    private String url;
    private Permissions permissions;
}
