package com.crowdin.client.applications.consents.model;

import lombok.Data;

import java.util.List;

@Data
public class AddApplicationConsentRequest {
    private String identifier;
    private Long installedBy;
    private ConsentStatus status;
    private List<String> scopes;
}
