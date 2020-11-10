package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public class Assignee {

    private Long id;
    private String username;
    private String fullName;
    private String avatarUrl;
    private Integer wordsCount;
    private Integer wordsLeft;
}
