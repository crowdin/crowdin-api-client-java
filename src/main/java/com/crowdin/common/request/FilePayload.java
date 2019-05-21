package com.crowdin.common.request;

import java.util.List;

public class FilePayload implements Request {
    private Long branchId;
    private Long directoryId;
    private Long storageId;
    private String name;
    private String title;
    private String exportPattern;
    private String type;
    private boolean firstLineContainsHeader;
    private boolean importTranslations;

    private String scheme;
    private boolean translateContent;
    private boolean translateAttributes;
    private boolean contentSegmentation;

    private List<String> translatableElements;
    private int escapeQuotes;

    public List<String> getTranslatableElements() {
        return translatableElements;
    }

    public void setTranslatableElements(List<String> translatableElements) {
        this.translatableElements = translatableElements;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Long directoryId) {
        this.directoryId = directoryId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExportPattern() {
        return exportPattern;
    }

    public void setExportPattern(String exportPattern) {
        this.exportPattern = exportPattern;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFirstLineContainsHeader() {
        return firstLineContainsHeader;
    }

    public void setFirstLineContainsHeader(boolean firstLineContainsHeader) {
        this.firstLineContainsHeader = firstLineContainsHeader;
    }

    public boolean isImportTranslations() {
        return importTranslations;
    }

    public void setImportTranslations(boolean importTranslations) {
        this.importTranslations = importTranslations;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public boolean isTranslateContent() {
        return translateContent;
    }

    public void setTranslateContent(boolean translateContent) {
        this.translateContent = translateContent;
    }

    public boolean isTranslateAttributes() {
        return translateAttributes;
    }

    public void setTranslateAttributes(boolean translateAttributes) {
        this.translateAttributes = translateAttributes;
    }

    public boolean isContentSegmentation() {
        return contentSegmentation;
    }

    public void setContentSegmentation(boolean contentSegmentation) {
        this.contentSegmentation = contentSegmentation;
    }

    public int getEscapeQuotes() {
        return escapeQuotes;
    }

    public void setEscapeQuotes(int escapeQuotes) {
        this.escapeQuotes = escapeQuotes;
    }
}
