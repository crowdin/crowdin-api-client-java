package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class GlossaryImportStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;
    @Deprecated
    private String eta;

    @Data
    public static class Attributes {

        private Long storageId;
        private Map<String, Integer> scheme;
        private boolean firstLineContainsHeader;

    }
}
