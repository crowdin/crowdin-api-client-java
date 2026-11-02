package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VSDX file format settings.
 * Extends {@link OtherFileFormatSettings} for backward compatibility: these settings used to be returned as {@link OtherFileFormatSettings}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VsdxFileFormatSettings extends OtherFileFormatSettings {
    private Boolean cleanTagsAggressively;
    private Boolean translateHyperlinkUrls;
    private Boolean customSegmentation;
}
