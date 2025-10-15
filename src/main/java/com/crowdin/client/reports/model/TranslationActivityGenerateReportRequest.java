package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TranslationActivityGenerateReportRequest extends GenerateReportRequest {
    private String name = "translation-activity";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
    }
}
