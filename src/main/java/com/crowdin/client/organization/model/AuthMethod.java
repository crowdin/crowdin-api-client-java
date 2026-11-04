package com.crowdin.client.organization.model;

import lombok.Data;

@Data
public class AuthMethod {

    private String name;
    private Boolean isEnabled;
    private Boolean isDefault;
}
