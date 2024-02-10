package com.crowdin.client.applications.installations.model;

import lombok.Data;

@Data
public class Permissions {

    private User user;
    private Project project;

}
