package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public class CreateTaskCommentRequest {
    private String text;
    private Long timeSpent;
}
