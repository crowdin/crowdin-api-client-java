package com.crowdin.client.stringcomments.model;

import lombok.Data;

import java.util.Date;

@Data
public class StringComment {

    private Long id;
    private String text;
    private Long userId;
    private Long stringId;
    private User user;
    private StringObject string;
    private String languageId;
    private Type type;
    private String issueType;
    private IssueStatus issueStatus;
    private Date createdAt;

    @Data
    public static class User {

        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }

    @Data
    public static class StringObject {
        private Long id;
        private String text;
        private String type;
        @Deprecated
        private Boolean hasPlurals;
        @Deprecated
        private Boolean isIcu;
        private String context;
        private Long fileId;
    }
}