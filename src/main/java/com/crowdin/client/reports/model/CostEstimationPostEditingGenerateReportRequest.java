package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CostEstimationPostEditingGenerateReportRequest extends GenerateReportRequest {
    private String name = "costs-estimation-pe";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean calculateInternalMatches;
        private Boolean includePreTranslatedStrings;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
        private String languageId;
        private List<Long> fileIds;
        private List<Long> directoryIds;
        private List<Long> branchIds;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> labelIds;
        private LabelIncludeType labelIncludeType;
        private Long workflowStepId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ByTaskSchema extends Schema {
        private Long taskId;
    }
}
