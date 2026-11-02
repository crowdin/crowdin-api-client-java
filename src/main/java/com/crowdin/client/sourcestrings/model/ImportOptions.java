package com.crowdin.client.sourcestrings.model;

import lombok.Data;

@Data
public class ImportOptions {

    /**
     * Spreadsheet files
     */
    private Boolean firstLineContainsHeader;
    /**
     * StringCatalog files. Import translations from the file
     */
    private Boolean importTranslations;
    /**
     * Spreadsheet files
     */
    private Object scheme;
    /**
     * StringCatalog files. Import the key as source string if it does not exist
     */
    private Boolean importKeyAsSource;
    /**
     * Other files
     */
    private Boolean contentSegmentation;
    /**
     * Other files. Storage identifier of the SRX segmentation rules file
     */
    private Long srxStorageId;
}
