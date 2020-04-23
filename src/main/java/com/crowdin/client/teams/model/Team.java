package com.crowdin.client.teams.model;

import lombok.Data;

import java.util.Date;

@Data
public class Team {

    private Long id;
    private String name;
    private Integer totalMembers;
    private Date createdAt;
    private Date updatedAt;
}
