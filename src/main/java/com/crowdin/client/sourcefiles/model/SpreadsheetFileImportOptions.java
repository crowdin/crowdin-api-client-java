package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Map;

@Data
public class SpreadsheetFileImportOptions extends ImportOptions {

    private Boolean importTranslations;
    private Map<String, Integer> scheme;
}
