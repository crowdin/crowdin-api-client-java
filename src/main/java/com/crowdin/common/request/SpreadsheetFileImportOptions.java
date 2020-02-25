package com.crowdin.common.request;

import java.util.Map;

public class SpreadsheetFileImportOptions implements ImportOptions {

    private Map<String, Integer> scheme;
    private boolean firstLineContainsHeader;

    public Map<String, Integer> getScheme() {
        return scheme;
    }

    public void setScheme(Map<String, Integer> scheme) {
        this.scheme = scheme;
    }

    public boolean isFirstLineContainsHeader() {
        return firstLineContainsHeader;
    }

    public void setFirstLineContainsHeader(boolean firstLineContainsHeader) {
        this.firstLineContainsHeader = firstLineContainsHeader;
    }
}
