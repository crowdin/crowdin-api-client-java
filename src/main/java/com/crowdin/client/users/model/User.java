package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private Boolean emailVerified;
    /**
     * Crowdin only
     */
    private String fullName;
    private String firstName;
    private String lastName;
    private Status status;
    private String avatarUrl;
    private Date createdAt;
    private Date lastSeen;
    private TwoFactor twoFactor;
    private boolean isAdmin;
    private String timezone;
    /**
     * Enterprise only. Keys are field slugs
     */
    private Map<String, Object> fields;
    /**
     * Enterprise only
     */
    private JoinDetails joinDetails;
    /**
     * Enterprise only. {@code enabled} or {@code disabled}
     */
    private String deviceVerification;
    /**
     * Enterprise only
     */
    private Integer trustedDevicesCount;
    /**
     * Enterprise only
     */
    private Integer apiTokensCount;
    /**
     * Enterprise only
     */
    private List<String> loginMethods;
    /**
     * Enterprise only
     */
    private List<String> mfaMethods;

    @Data
    public static class JoinDetails {
        private String type;
        private InvitedBy invitedBy;
    }

    @Data
    public static class InvitedBy {
        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }
}
