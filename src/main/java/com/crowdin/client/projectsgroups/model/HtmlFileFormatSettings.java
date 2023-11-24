package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HtmlFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    private Boolean excludedElements;
    private Long srxStorageId;
}
