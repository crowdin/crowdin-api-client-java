package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Vdf file format settings.
 * Extends {@link OtherFileFormatSettings} for backward compatibility: these settings used to be returned as {@link OtherFileFormatSettings}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VdfFileFormatSettings extends OtherFileFormatSettings {
    private Boolean convertIcu;
    private Boolean addGenderArgument;
}
