package com.crowdin.common.models;

public class Settings {

    private Long projectId;
    private int translateDuplicates;
    private boolean isMtAllowed;
    private boolean autoSubstitution;
    private boolean autoTranslateDialects;
    private boolean publicDownloads;
    private boolean useGlobalTm;
    private boolean inContext;
    private String jiptPseudoLanguageId;
    private boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

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

    public String getJiptPseudoLanguageId() {
        return jiptPseudoLanguageId;
    }

    public void setJiptPseudoLanguageId(String jiptPseudoLanguageId) {
        this.jiptPseudoLanguageId = jiptPseudoLanguageId;
    }

    public boolean isQaCheckIsActive() {
        return qaCheckIsActive;
    }

    public void setQaCheckIsActive(boolean qaCheckIsActive) {
        this.qaCheckIsActive = qaCheckIsActive;
    }

    public QaCheckCategories getQaCheckCategories() {
        return qaCheckCategories;
    }

    public void setQaCheckCategories(QaCheckCategories qaCheckCategories) {
        this.qaCheckCategories = qaCheckCategories;
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
}
