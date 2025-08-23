package com.crowdin.client.stringcorrections.model;

import lombok.Data;

import java.util.Date;

@Data
public class Correction {

    private Long id;
    private String text;
    private String pluralCategoryName;
    private User user;
    private Date createdAt;

    @Data
    public static class User {
        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }
}
