package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class InstallApplicationRequestObject {

    private String url;
    private Permissions permissions;
}
