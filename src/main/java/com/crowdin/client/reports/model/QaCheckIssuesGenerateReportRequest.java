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
        private String unit;
        private String languageId;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
    }
}
