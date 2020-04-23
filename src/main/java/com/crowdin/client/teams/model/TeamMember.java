package com.crowdin.client.teams.model;

import lombok.Data;

import java.util.Date;

@Data
public class TeamMember {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private Date addedAt;
}
