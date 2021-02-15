package com.crowdin.client.reports.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReportStatus {

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

        private ReportsFormat format;
        private String reportName;
        private Object schema;
    }
}
