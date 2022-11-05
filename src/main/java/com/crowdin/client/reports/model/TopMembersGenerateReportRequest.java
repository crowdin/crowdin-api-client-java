package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TopMembersGenerateReportRequest extends GenerateReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
    }

}
