package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.EscapeQuotesMode;
import com.crowdin.client.core.model.EscapeSpecialCharsMode;
import lombok.Data;

@Data
public class PropertyFileFormatSettings extends FileFormatSettings {
    private EscapeQuotesMode escapeQuotes;
    private EscapeSpecialCharsMode escapeSpecialCharacters;
}
