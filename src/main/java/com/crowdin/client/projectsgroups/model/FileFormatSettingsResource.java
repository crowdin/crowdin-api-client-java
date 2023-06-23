package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FileFormatSettingsResource {
    private Long id;
    private String name;
    private String format;
    private List<String> extensions;
    private FileFormatSettings settings;
    private Date createdAt;
    private Date updatedAt;
}
