package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ImportTranslationsStringsBasedStatus {

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
        private Long storageId;
        private Long branchId;
        private Boolean importEqSuggestions;
        private Boolean autoApproveImported;
        private Boolean translateHidden;
        private Boolean addToTm;
        private List<String> languageIds;
    }
}
