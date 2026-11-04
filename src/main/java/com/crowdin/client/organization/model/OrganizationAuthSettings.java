package com.crowdin.client.organization.model;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationAuthSettings {

    private Boolean allowSignUp;
    private Boolean twoFactorAuthentication;
    private List<AuthMethod> authMethods;
}
