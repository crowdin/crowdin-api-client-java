package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PreTranslationStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;

    @Data
    public static class Attributes {
        private List<String> languageIds;
        private List<Long> fileIds;
        private List<Long> branchIds;
        private Method method;
        private AutoApproveOption autoApproveOption;
        private boolean duplicateTranslations;
        private boolean skipApprovedTranslations;
        private boolean translateUntranslatedOnly;
        private boolean translateWithPerfectMatchOnly;
        private List<Long> labelIds;
        private List<Long> excludeLabelIds;
        private Integer minimumMatchRatio;
    }
}
