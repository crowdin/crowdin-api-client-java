package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TranslateAccuracyGenerateReportRequest extends GenerateReportRequest {
    private String name = "translator-accuracy";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private ReportsFormat format;
        private List<String> postEditingCategories;
        private String languageId;
        private List<Long> userIds;
        private Date dateFrom;
        private Date dateTo;
    }

}
