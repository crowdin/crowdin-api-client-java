package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public class AddTaskSettingsTemplateRequest {
    private String name;
    private TaskSettingsTemplateConfig config;
}
