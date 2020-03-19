package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Map;

@Data
public class SpreadsheetFileImportOptions extends ImportOptions {

    private boolean firstLineContainsHeader;
    private boolean importTranslations;
    private Map<String, Integer> scheme;
}
