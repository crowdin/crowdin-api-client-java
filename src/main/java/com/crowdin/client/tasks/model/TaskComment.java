package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;

@Data
public class TaskComment {
    private Long id;
    private Long userId;
    private Long taskId;
    private String text;
    private Long timeSpent;
    private Date createdAt;
    private Date updatedAt;
}
