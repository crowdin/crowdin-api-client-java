package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class DocxFileFormatSettings extends FileFormatSettings {
    private Boolean cleanTagsAggressively;
    private Boolean translateHiddenText;
    private Boolean translateHyperlinkUrls;
    private Boolean translateHiddenRowsAndColumns;
    private Boolean importNotes;
    private Boolean importHiddenSlides;
    private Boolean contentSegmentation;
    private Long srxStorageId;
}
