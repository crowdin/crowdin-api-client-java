package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiFileTranslation {
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
        private String stage;
        private Error error;
        private String downloadName;
        private String sourceLanguageId;
        private String targetLanguageId;
        private String originalFileName;
        private String detectedType;
        private Integer parserVersion;
    }

    @Data
    public static class Error {
        private String stage;
        private String message;
    }
}
