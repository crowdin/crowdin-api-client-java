package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class MacOSXFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
