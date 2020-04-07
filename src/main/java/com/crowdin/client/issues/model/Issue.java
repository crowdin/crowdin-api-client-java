package com.crowdin.client.issues.model;

import lombok.Data;

import java.util.Date;

@Data
public class Issue {

    private Long id;
    private String text;
    private Long userId;
    private Long stringId;
    private String languageId;
    private Type type;
    private Status status;
    private Date createdAt;
}
