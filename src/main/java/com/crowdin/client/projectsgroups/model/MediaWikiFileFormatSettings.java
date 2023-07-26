package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaWikiFileFormatSettings extends FileFormatSettings {
    private Long srxStorageId;
}
