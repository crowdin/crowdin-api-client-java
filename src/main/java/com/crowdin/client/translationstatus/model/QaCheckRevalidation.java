package com.crowdin.client.translationstatus.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QaCheckRevalidation {
    private String identifier;
    private String status;
    private Long progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;

    @Data
    public static class Attributes {
        private List<String> languageIds;
        private List<String> qaCheckCategories;
        private Boolean failedOnly;
    }
}
