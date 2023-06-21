package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.JsonFileType;
import lombok.Data;

@Data
public class JsonFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private JsonFileType type;
}
