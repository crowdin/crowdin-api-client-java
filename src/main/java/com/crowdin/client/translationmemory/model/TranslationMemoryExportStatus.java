package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Date;

@Data
public class TranslationMemoryExportStatus {

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

        private String sourceLanguageId;
        private String targetLanguageId;
        private TranslationMemoryFormat format;
    }
}
