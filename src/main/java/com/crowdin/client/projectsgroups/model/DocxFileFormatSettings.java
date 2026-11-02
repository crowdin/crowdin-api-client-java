package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocxFileFormatSettings extends FileFormatSettings {
    private Boolean cleanTagsAggressively;
    private Boolean translateHiddenText;
    private Boolean translateHyperlinkUrls;
    private Boolean translateHiddenRowsAndColumns;
    private Boolean importNotes;
    private Boolean importHiddenSlides;
    private Boolean contentSegmentation;
    private Long srxStorageId;
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
    private Boolean allowWordStyleOptimization;
    private Boolean translateExcelExcludeColors;
    private Boolean customSegmentation;
}
