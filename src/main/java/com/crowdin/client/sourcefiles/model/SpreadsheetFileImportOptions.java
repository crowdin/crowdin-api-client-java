package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpreadsheetFileImportOptions extends ImportOptions {

    private Boolean firstLineContainsHeader;
    private Boolean importHiddenSheets;
    private Boolean importHiddenRows;
    private Boolean importEqSuggestions;
    private Boolean autoApproveImported;
    private Boolean translateHidden;
    private Boolean addToTm;
    private Boolean contentSegmentation;
    /**
     * Returned in responses only.
     */
    private Boolean customSegmentation;
    private Long srxStorageId;
    /**
     * Accepted in requests. Marked as deprecated in the API response schema.
     */
    private Boolean importTranslations;
    private Map<String, Integer> scheme;
}
