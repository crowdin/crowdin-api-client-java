package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class TeamMember {

    private Long id;
    private String username;
    private String fullName;
    private String role;
    private Map<String, String> permissions;
    private String avatarUrl;
    private Date joinedAt;
    private String timezone;
}
