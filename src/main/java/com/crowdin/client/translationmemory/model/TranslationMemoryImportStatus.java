package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class TranslationMemoryImportStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;
    private String eta;

    @Data
    public static class Attributes {

        private Long tmId;
        private Long storageId;
        private boolean firstLineContainsHeader;
        private Map<String, Integer> scheme;
        private Long organizationId;
        private String progressKey;
        private Long userId;
    }
}
