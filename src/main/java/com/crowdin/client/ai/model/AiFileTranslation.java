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
        private FileTranslationError error;
        private String downloadName;
        private String sourceLanguageId;
        private String targetLanguageId;
        private String originalFileName;
        private String detectedType;
        private Integer parserVersion;
    }

    @Data
    private static class FileTranslationError {
        private String stage;
        private String message;
    }
}
