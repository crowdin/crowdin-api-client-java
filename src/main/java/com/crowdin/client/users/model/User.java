package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Status status;
    private String avatarUrl;
    private Date createdAt;
    private Date lastSeen;
    private TwoFactor twoFactor;
    private boolean isAdmin;
    private String timezone;
}
