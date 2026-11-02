package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Xliff 2.0 file format settings.
 * Extends {@link OtherFileFormatSettings} for backward compatibility: these settings used to be returned as {@link OtherFileFormatSettings}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class XliffTwoFileFormatSettings extends OtherFileFormatSettings {
    private Boolean contentSegmentation;
    private Long srxStorageId;
    private Boolean customSegmentation;
}
