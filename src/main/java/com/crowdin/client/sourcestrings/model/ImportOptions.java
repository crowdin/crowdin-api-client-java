package com.crowdin.client.sourcestrings.model;

import lombok.Data;

@Data
public class ImportOptions {

    private Boolean firstLineContainsHeader;
    private Boolean importTranslations;
    private Object scheme;
}
