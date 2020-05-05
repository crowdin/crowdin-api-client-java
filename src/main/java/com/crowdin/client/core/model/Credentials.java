package com.crowdin.client.core.model;

public class Credentials {

    private final String token;
    private final String organization;
    private String baseUrl;

    public Credentials(String token, String organization) {
        this.token = token;
        this.organization = organization;
    }

    public Credentials(String token, String organization, String baseUrl) {
        this.token = token;
        this.organization = organization;
        this.baseUrl = baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getOrganization() {
        return organization;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
