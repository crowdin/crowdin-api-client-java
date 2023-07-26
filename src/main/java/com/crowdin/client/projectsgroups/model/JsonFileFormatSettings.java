package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.JsonFileType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JsonFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private JsonFileType type;
}
