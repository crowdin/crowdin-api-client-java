package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebXmlFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private List<String> inlineTags;
    private Boolean hideAttributeValues;
    private Boolean customSegmentation;
}
