package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TranslationCostsPostEndingGenerateReportRequest extends GenerateReportRequest {
    private String name = "translation-costs-pe";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private Boolean useCategoryBasedProofreadRates;
        private Boolean useTmEditDistance;
        /**
         * @deprecated deprecated in the API
         */
        @Deprecated
        private Boolean excludeApprovalsForEditedTranslations;
        /**
         * @deprecated deprecated in the API
         */
        @Deprecated
        private Boolean preTranslatedStringsCategorizationAdjustment;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
        private String groupBy;
        private Date dateFrom;
        private Date dateTo;
        private String languageId;
        private List<Long> userIds;
        private List<Long> fileIds;
        private List<Long> directoryIds;
        private List<Long> branchIds;
        private List<Long> labelIds;
        private LabelIncludeType labelIncludeType;
        private Long workflowStepId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ByTaskSchema extends Schema {
        /**
         * @deprecated deprecated in the API, use {@link #taskIds} instead
         */
        @Deprecated
        private Long taskId;
        private List<Long> taskIds;
    }
}
