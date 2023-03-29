package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class DocxFileImportOptions extends ImportOptions {

    private Boolean cleanTagsAggressively;
    private Boolean translateHiddenText;
    private Boolean translateHyperlinkUrls;
    private Boolean translateHiddenRowsAndColumns;
    private Boolean importNotes;
    private Boolean importHiddenSlides;
    private Boolean contentSegmentation;
    private Integer srxStorageId;

}
