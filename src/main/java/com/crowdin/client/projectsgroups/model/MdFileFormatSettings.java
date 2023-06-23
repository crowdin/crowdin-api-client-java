package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class MdFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
