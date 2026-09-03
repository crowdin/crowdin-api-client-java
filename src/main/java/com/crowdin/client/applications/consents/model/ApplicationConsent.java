package com.crowdin.client.applications.consents.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationConsent {

    private Long id;
    private User installedBy;
    private String identifier;
    private String name;
    private ConsentStatus status;
    private String[] scopes;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class User {
        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }
}
