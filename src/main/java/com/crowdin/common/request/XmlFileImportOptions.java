package com.crowdin.common.request;

import java.util.List;

public class XmlFileImportOptions implements ImportOptions {

    private boolean translateContent;
    private boolean translateAttributes;
    private boolean contentSegmentation;
    private List<String> translatableElements;

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

    public List<String> getTranslatableElements() {
        return translatableElements;
    }

    public void setTranslatableElements(List<String> translatableElements) {
        this.translatableElements = translatableElements;
    }
}
