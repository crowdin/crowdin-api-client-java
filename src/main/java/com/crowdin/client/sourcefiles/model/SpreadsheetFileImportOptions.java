package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpreadsheetFileImportOptions extends ImportOptions {

    private Boolean firstLineContainsHeader;
    private Boolean importTranslations;
    private Map<String, Integer> scheme;
}
