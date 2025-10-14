package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SourceContentUpdatesGenerateReportRequest extends GenerateReportRequest {
    private String name = "source-content-updates";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;
        private Date dateFrom;    // Probably optional date filtering
        private Date dateTo;
    }
}
