package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EscapeQuotesMode;
import com.crowdin.client.core.model.EscapeSpecialCharsMode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PropertiesFileFormatSettings extends FileFormatSettings {
    private EscapeQuotesMode escapeQuotes;
    private EscapeSpecialCharsMode escapeSpecialCharacters;
}
