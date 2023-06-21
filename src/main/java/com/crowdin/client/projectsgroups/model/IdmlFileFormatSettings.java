package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class IdmlFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
