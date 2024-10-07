package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public abstract class AddTaskRequest {
    private String title;
    private String description;
}
