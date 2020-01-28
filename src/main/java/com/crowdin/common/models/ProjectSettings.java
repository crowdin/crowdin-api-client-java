package com.crowdin.common.models;

import java.util.Map;

public class ProjectSettings extends Project {

    private int translateDuplicates;
    private boolean isMtAllowed;
    private boolean autoSubstitution;
    private boolean exportTranslatedOnly;
    private boolean exportApprovedOnly;
    private boolean autoTranslateDialects;
    private boolean publicDownloads;
    private boolean useGlobalTm;
    private boolean inContext;
    private String inContextPseudoLanguageId;
    private boolean qaCheckIsActive;
    private int lowestQualityProjectGoalId;
    private QaCheckCategories qaCheckCategories;
//    When field is empty, server returns empty array instead of empty object
//    Should have been Map<String, ProjectSettings.LanguageMapping>
    private Object languageMapping;

    public int getTranslateDuplicates() {
        return translateDuplicates;
    }

    public void setTranslateDuplicates(int translateDuplicates) {
        this.translateDuplicates = translateDuplicates;
    }

    public boolean isMtAllowed() {
        return isMtAllowed;
    }

    public void setMtAllowed(boolean mtAllowed) {
        isMtAllowed = mtAllowed;
    }

    public boolean isAutoSubstitution() {
        return autoSubstitution;
    }

    public void setAutoSubstitution(boolean autoSubstitution) {
        this.autoSubstitution = autoSubstitution;
    }

    public boolean isExportTranslatedOnly() {
        return exportTranslatedOnly;
    }

    public void setExportTranslatedOnly(boolean exportTranslatedOnly) {
        this.exportTranslatedOnly = exportTranslatedOnly;
    }

    public boolean isExportApprovedOnly() {
        return exportApprovedOnly;
    }

    public void setExportApprovedOnly(boolean exportApprovedOnly) {
        this.exportApprovedOnly = exportApprovedOnly;
    }

    public boolean isAutoTranslateDialects() {
        return autoTranslateDialects;
    }

    public void setAutoTranslateDialects(boolean autoTranslateDialects) {
        this.autoTranslateDialects = autoTranslateDialects;
    }

    public boolean isPublicDownloads() {
        return publicDownloads;
    }

    public void setPublicDownloads(boolean publicDownloads) {
        this.publicDownloads = publicDownloads;
    }

    public boolean isUseGlobalTm() {
        return useGlobalTm;
    }

    public void setUseGlobalTm(boolean useGlobalTm) {
        this.useGlobalTm = useGlobalTm;
    }

    public boolean isInContext() {
        return inContext;
    }

    public void setInContext(boolean inContext) {
        this.inContext = inContext;
    }

    public String getInContextPseudoLanguageId() {
        return inContextPseudoLanguageId;
    }

    public void setInContextPseudoLanguageId(String inContextPseudoLanguageId) {
        this.inContextPseudoLanguageId = inContextPseudoLanguageId;
    }

    public boolean isQaCheckIsActive() {
        return qaCheckIsActive;
    }

    public void setQaCheckIsActive(boolean qaCheckIsActive) {
        this.qaCheckIsActive = qaCheckIsActive;
    }

    public int getLowestQualityProjectGoalId() {
        return lowestQualityProjectGoalId;
    }

    public void setLowestQualityProjectGoalId(int lowestQualityProjectGoalId) {
        this.lowestQualityProjectGoalId = lowestQualityProjectGoalId;
    }

    public QaCheckCategories getQaCheckCategories() {
        return qaCheckCategories;
    }

    public void setQaCheckCategories(QaCheckCategories qaCheckCategories) {
        this.qaCheckCategories = qaCheckCategories;
    }

    public Object getLanguageMapping() {
        return languageMapping;
    }

    public void setLanguageMapping(Object languageMapping) {
        this.languageMapping = languageMapping;
    }

    public class QaCheckCategories {
        private boolean empty;
        private boolean size;
        private boolean tags;
        private boolean spaces;
        private boolean variables;
        private boolean punctuation;
        private boolean symbolRegister;
        private boolean specialSymbols;
        private boolean wrongTranslation;
        private boolean spellcheck;
        private boolean icu;

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public boolean isSize() {
            return size;
        }

        public void setSize(boolean size) {
            this.size = size;
        }

        public boolean isTags() {
            return tags;
        }

        public void setTags(boolean tags) {
            this.tags = tags;
        }

        public boolean isSpaces() {
            return spaces;
        }

        public void setSpaces(boolean spaces) {
            this.spaces = spaces;
        }

        public boolean isVariables() {
            return variables;
        }

        public void setVariables(boolean variables) {
            this.variables = variables;
        }

        public boolean isPunctuation() {
            return punctuation;
        }

        public void setPunctuation(boolean punctuation) {
            this.punctuation = punctuation;
        }

        public boolean isSymbolRegister() {
            return symbolRegister;
        }

        public void setSymbolRegister(boolean symbolRegister) {
            this.symbolRegister = symbolRegister;
        }

        public boolean isSpecialSymbols() {
            return specialSymbols;
        }

        public void setSpecialSymbols(boolean specialSymbols) {
            this.specialSymbols = specialSymbols;
        }

        public boolean isWrongTranslation() {
            return wrongTranslation;
        }

        public void setWrongTranslation(boolean wrongTranslation) {
            this.wrongTranslation = wrongTranslation;
        }

        public boolean isSpellcheck() {
            return spellcheck;
        }

        public void setSpellcheck(boolean spellcheck) {
            this.spellcheck = spellcheck;
        }

        public boolean isIcu() {
            return icu;
        }

        public void setIcu(boolean icu) {
            this.icu = icu;
        }
    }

    public class LanguageMapping {
        private String name;
        private String twoLettersCode;
        private String threeLettersCode;
        private String locale;
        private String localeWithUnderScore;
        private String androidCode;
        private String osxCode;
        private String osxLocale;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTwoLettersCode() {
            return twoLettersCode;
        }

        public void setTwoLettersCode(String twoLettersCode) {
            this.twoLettersCode = twoLettersCode;
        }

        public String getThreeLettersCode() {
            return threeLettersCode;
        }

        public void setThreeLettersCode(String threeLettersCode) {
            this.threeLettersCode = threeLettersCode;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getLocaleWithUnderScore() {
            return localeWithUnderScore;
        }

        public void setLocaleWithUnderScore(String localeWithUnderScore) {
            this.localeWithUnderScore = localeWithUnderScore;
        }

        public String getAndroidCode() {
            return androidCode;
        }

        public void setAndroidCode(String androidCode) {
            this.androidCode = androidCode;
        }

        public String getOsxCode() {
            return osxCode;
        }

        public void setOsxCode(String osxCode) {
            this.osxCode = osxCode;
        }

        public String getOsxLocale() {
            return osxLocale;
        }

        public void setOsxLocale(String osxLocale) {
            this.osxLocale = osxLocale;
        }
    }
}
