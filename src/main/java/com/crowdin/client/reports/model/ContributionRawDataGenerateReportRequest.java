package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContributionRawDataGenerateReportRequest extends GenerateReportRequest {
    private String name = "contribution-raw-data";
    private Schema schema;

    @Data
    public static class Schema {
        private String mode;
        private Unit unit;
        private List<String> columns;
        private List<Long> tmIds;
        private List<Long> mtIds;
        private List<Long> aiPromptIds;
        private Date dateFrom;
        private Date dateTo;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
        private String languageId;
        private Long userId;
        private List<Long> fileIds;
        private List<Long> directoryIds;
        private List<Long> branchIds;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ByTaskSchema extends Schema {
        private Long taskId;
    }
}
