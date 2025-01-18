package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PreTranslateAccuracyGenerateReportRequest extends GenerateReportRequest {
    private String name = "pre-translate-accuracy";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit = Unit.WORDS;
        private ReportsFormat format = ReportsFormat.XLSX;
        private List<String> postEditingCategories;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
        private String languageId;
        private Date dateFrom;
        private Date dateTo;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ByTaskSchema extends Schema {
        private Long taskId;
    }

}
