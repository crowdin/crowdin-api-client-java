package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;

@Data
public class StringsExporterSettingsResource {
    private Long id;
    private String format;
    private StringsExporterSettings settings;
    private Date createdAt;
    private Date updatedAt;
}
