package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskSettingsTemplateConfig {
    private List<LanguageReference> languages;
}
