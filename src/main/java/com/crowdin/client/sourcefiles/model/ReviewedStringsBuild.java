package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class ReviewedStringsBuild {

    private Long id;
    private Long projectId;
    private String status;
    private Long progress;
    private Attributes attributes;

    @Data
    public static class Attributes {
        private Long branchId;
        private String targetLanguageId;
    }
}
