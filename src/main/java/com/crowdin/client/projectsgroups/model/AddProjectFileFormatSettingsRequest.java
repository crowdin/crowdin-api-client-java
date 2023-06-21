package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class AddProjectFileFormatSettingsRequest {
    private String format;
    private FileFormatSettings settings;
}
