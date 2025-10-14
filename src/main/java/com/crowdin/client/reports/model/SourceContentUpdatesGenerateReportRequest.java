package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SourceContentUpdatesGenerateReportRequest extends GenerateReportRequest {
    private String name = "source-content-updates";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;

    }
}
