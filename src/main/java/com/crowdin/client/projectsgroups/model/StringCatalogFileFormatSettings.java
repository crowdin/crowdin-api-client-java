package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * String Catalog file format settings.
 * Extends {@link OtherFileFormatSettings} for backward compatibility: these settings used to be returned as {@link OtherFileFormatSettings}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StringCatalogFileFormatSettings extends OtherFileFormatSettings {
    private Boolean importKeyAsSource;
    private Boolean importTranslations;
}
