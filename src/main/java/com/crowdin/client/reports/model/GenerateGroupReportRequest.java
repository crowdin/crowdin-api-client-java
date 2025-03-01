package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
public class GenerateGroupReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static abstract class Schema {

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslationCostsPostEditingSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean excludeApprovalsForEditedTranslations;
        private Boolean preTranslatedStringsCategorizationAdjustment;
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> userIds;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTopMembersSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
    }
}
