package com.crowdin.client.reports.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTranslationCostsPostEditingGenerateGroupReportRequest extends GenerateGroupReportRequest {
    @Setter(AccessLevel.NONE)
    private String name = "group-translation-costs-pe";
    private Schema schema;

    @Data
    public static abstract class Schema {

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
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
        private List<Long> userIds;
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
