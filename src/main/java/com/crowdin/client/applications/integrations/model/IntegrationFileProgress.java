package com.crowdin.client.applications.integrations.model;

import lombok.Data;

@Data
public class IntegrationFileProgress {

    private String languageId;
    private String eTag;
    private Count words;
    private Count phrases;
    private Integer translationProgress;
    private Integer approvalProgress;

    @Data
    public static class Count {
        private Integer total;
        private Integer translated;
        private Integer approved;
    }
}
