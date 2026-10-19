package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Import options for Office documents (docx, xlsx, pptx and similar formats).
 * Also returned by the API for vsdx files.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DocxFileImportOptions extends ImportOptions {

    private Boolean cleanTagsAggressively;
    private Boolean translateHiddenText;
    private Boolean translateHyperlinkUrls;
    private Boolean translateHiddenRowsAndColumns;
    private Boolean importNotes;
    private Boolean importHiddenSlides;
    private Boolean contentSegmentation;
    /**
     * Returned in responses only.
     */
    private Boolean customSegmentation;
    private Integer srxStorageId;
    private Boolean translateDocProperties;
    private Boolean translateComments;
    private Boolean ignoreWhitespaceStyles;
    private Boolean addTabAsCharacter;
    private Boolean addLineSeparatorAsCharacter;
    private String lineSeparatorReplacement;
    private Boolean replaceNoBreakHyphenTag;
    private Boolean ignoreSoftHyphenTag;
    private List<String> complexFieldDefinitionsToExtract;
    private Boolean translateWordHeadersFooters;
    private Boolean translateWordGraphicName;
    private Boolean translateWordGraphicDescription;
    private Boolean ignoreWordFontColors;
    private String wordFontColorsMinIgnoranceThreshold;
    private String wordFontColorsMaxIgnoranceThreshold;
    private List<String> excludeWordStyles;
    private Boolean translateWordInExcludeStyleMode;
    private List<String> wordHighlightColors;
    private Boolean translateWordInExcludeHighlightMode;
    private Boolean translateWordExcludeColors;
    private List<String> wordExcludedColors;
    private Boolean translateExcelCellsCopied;
    private Boolean translateExcelSheetNames;
    private List<String> excelExcludedColors;
    private Boolean translateExcelDiagramData;
    private Boolean translateExcelDrawings;

}
