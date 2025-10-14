package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class EditorIssuesGenerateReportRequest extends GenerateReportRequest {
    private String name = "editor-issues";
    private Schema schema;

    @Data
    public static class Schema {
        private Date dateFrom;
        private Date dateTo;
        private ReportsFormat format;
        private String issueType;
    }
}
