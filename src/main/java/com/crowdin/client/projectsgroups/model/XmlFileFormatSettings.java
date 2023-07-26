package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class XmlFileFormatSettings extends FileFormatSettings {
    private Boolean translateContent;
    private Boolean translateAttributes;
    private List<String> translatableElements;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
