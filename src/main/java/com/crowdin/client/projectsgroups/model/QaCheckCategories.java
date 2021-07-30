package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class QaCheckCategories {
    private Boolean empty;
    private Boolean size;
    private Boolean tags;
    private Boolean spaces;
    private Boolean variables;
    private Boolean punctuation;
    private Boolean symbolRegister;
    private Boolean specialSymbols;
    private Boolean wrongTranslation;
    private Boolean spellcheck;
    private Boolean icu;
    private Boolean terms;
    private Boolean duplicate;
}
