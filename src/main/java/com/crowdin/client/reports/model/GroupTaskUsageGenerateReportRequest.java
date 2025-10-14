package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTaskUsageGenerateReportRequest extends GenerateReportRequest {
    private String name = "group-task-usage";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> projectIds;
    }
}
