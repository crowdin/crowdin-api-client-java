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
    public static class GroupTranslationCostsPerEditingSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
        private List<String> languageId;
        private List<Long> userIds;
        private List<Long> branchIds;
        private List<Long> labelIds;
        private LabelIncludeType labelIncludeType;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslationCostsPerEditingByTaskSchema extends Schema {
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Long taskId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CostsEstimationSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean calculateInternalMatches;
        private Boolean includePreTranslatedStrings;
        private String languageId;
        private List<Long> branchIds;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> labelIds;
        private LabelIncludeType labelIncludeType;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CostsEstimationByTaskSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean calculateInternalMatches;
        private Boolean includePreTranslatedStrings;
        private Long taskId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslationCostSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
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

    @Data
    public static class IndividualRate {
        private List<String> languageIds;
        private List<Long> userIds;
        private Float fullTranslation;
        private Float proofread;
    }

    @Data
    public static class NetRateSchemes {
        private List<Match> tmMatch;
        private List<Match> mtMatch;
        private List<Match> suggestionMatch;
    }
}
