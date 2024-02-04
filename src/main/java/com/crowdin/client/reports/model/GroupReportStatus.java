package com.crowdin.client.reports.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GroupReportStatus {
    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;

    @Data
    public static class Attributes {
        private ReportsFormat format;
        private String reportName;
        private List<Long> projectIds;
        private Object schema;
    }
}
