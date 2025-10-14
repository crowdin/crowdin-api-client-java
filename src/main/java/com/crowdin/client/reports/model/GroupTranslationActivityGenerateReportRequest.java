package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTranslationActivityGenerateReportRequest extends GenerateReportRequest {
    private String name = "group-translation-activity";
    private Schema schema;

    @Data
    public static class Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean excludeApprovalsForEditedTranslations;
        private Boolean preTranslatedStringsCategorizationAdjustment;
        private String groupBy;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> userIds;
    }
}
