package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;

@Data
public class TaskSettingsTemplate {
    private Long id;
    private String name;
    private TaskSettingsTemplateConfig config;
    private Date createdAt;
    private Date updatedAt;
}
