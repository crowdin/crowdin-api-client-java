package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class InstallationUser {

    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
}
