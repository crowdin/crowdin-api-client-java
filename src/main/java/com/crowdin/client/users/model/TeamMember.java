package com.crowdin.client.users.model;

import lombok.Data;

import java.util.Date;

@Data
public class TeamMember {

    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
    private Date joinedAt;
    private String timezone;
}
