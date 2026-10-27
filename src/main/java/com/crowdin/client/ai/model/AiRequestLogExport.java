package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class AiRequestLogExport {
    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    private String eta;

    @Data
    public static class Attributes {
        private AiRequestLogExportFormat format;
        private Map<String, Object> filters;
    }
}
