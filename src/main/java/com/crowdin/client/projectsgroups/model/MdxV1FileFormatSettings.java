package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.List;

@Data
public class MdxV1FileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private List<String> excludedFrontMatterElements;
    private Boolean excludeCodeBlocks;
}
