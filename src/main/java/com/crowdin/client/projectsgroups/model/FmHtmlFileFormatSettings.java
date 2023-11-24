package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FmHtmlFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Boolean excludedElements;
    private List<String> excludedFrontMatterElements;
    private Long srxStorageId;
}
