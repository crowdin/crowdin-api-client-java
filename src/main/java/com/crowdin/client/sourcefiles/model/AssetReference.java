package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Date;

@Data
public class AssetReference {
    private Long id;
    private String name;
    private String url;
    private User user;
    private Date createdAt;
    private String mimeType;

    @Data
    public static class User {
        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }
}
