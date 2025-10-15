package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QaCheckIssuesGenerateReportRequest extends GenerateReportRequest {
    private String name = "qa-check-issues";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;
        private String languageId;
        private Date dateFrom;
        private Date dateTo;
    }
}
