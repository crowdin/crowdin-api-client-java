package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SavingActivityGenerateReportRequest extends GenerateReportRequest {
    private String name = "saving-activity";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        /**
         * Report mode: {@code currency} or {@code relative} (default)
         */
        private String mode;
        /**
         * File-based projects only
         */
        private List<Long> fileIds;
        /**
         * File-based projects only
         */
        private List<Long> directoryIds;
        private List<Long> branchIds;
        private List<Long> userIds;
    }
}