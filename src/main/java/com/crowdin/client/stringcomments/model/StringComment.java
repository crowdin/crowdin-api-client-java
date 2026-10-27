package com.crowdin.client.stringcomments.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StringComment {

    private Long id;
    private String text;
    private Long userId;
    private Long stringId;
    private User user;
    private StringObject string;
    private Long projectId;
    private String languageId;
    private Type type;
    private String issueType;
    private IssueStatus issueStatus;
    private Long resolverId;
    private User resolver;
    private Date resolvedAt;
    private Date createdAt;
    private List<Attachment> attachments;
    /**
     * File-based projects only, present for file comments
     */
    private Long fileId;
    /**
     * File-based projects only, present for file comments
     */
    private FileObject file;
    /**
     * Crowdin Enterprise only
     */
    private Boolean isShared;
    /**
     * Crowdin Enterprise only
     */
    private Organization senderOrganization;
    /**
     * Crowdin Enterprise only
     */
    private Organization resolverOrganization;

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

    @Data
    public static class FileObject {
        private Long id;
        private String name;
        private String type;
        private String context;
    }

    @Data
    public static class Organization {
        private Long id;
        private String domain;
    }
}