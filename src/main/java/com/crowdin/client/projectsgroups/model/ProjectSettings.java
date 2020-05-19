package com.crowdin.client.projectsgroups.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectSettings extends Project {

    private Integer translateDuplicates;
    private boolean isMtAllowed;
    private boolean autoSubstitution;
    private boolean exportTranslatedOnly;
    private boolean skipUntranslatedFiles;
    private boolean exportApprovedOnly;
    private boolean autoTranslateDialects;
    private boolean publicDownloads;
    private boolean useGlobalTm;
    private boolean inContext;
    private String inContextPseudoLanguageId;
    private boolean isSuspended;
    private boolean qaCheckIsActive;
    private QaCheckCategories qaCheckCategories;
    private List<Long> customQaCheckIds;
    private Map<String, Map<String, String>> languageMapping;

    @Data
    public static class QaCheckCategories {
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
    }
}
