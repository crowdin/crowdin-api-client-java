package com.crowdin.client.core.model;

public class CrowdinUrlBuilder implements UrlBuilder {

    @Override
    public String get() {
        return "https://api.crowdin.com/api/v2";
    }

    @Override
    public String getWithOrganization(String organization) {
        return "https://" + organization + ".api.crowdin.com/api/v2";
    }
}
