package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Group / organization report request (Crowdin Enterprise).
 * <p>
 * Set {@link #name} to the report name and {@link #schema} to the matching schema class:
 * <ul>
 * <li>{@code group-translation-costs-pe}: {@link GroupTranslationCostsPostEditingSchema}, {@link GroupTranslationCostsPostEditingByTaskSchema}</li>
 * <li>{@code group-top-members}: {@link GroupTopMembersSchema}</li>
 * <li>{@code group-task-usage}: {@link GroupTaskUsageSchema} subclasses</li>
 * <li>{@code group-qa-check-issues}: {@link GroupQaCheckIssuesSchema}</li>
 * <li>{@code group-translation-activity}: {@link GroupTranslationActivitySchema}</li>
 * <li>{@code group-source-content-updates}: {@link GroupSourceContentUpdatesSchema}</li>
 * <li>{@code group-time-spent}: {@link GroupTimeSpentSchema}</li>
 * <li>{@code group-pre-translate-accuracy}: {@link GroupPreTranslateAccuracySchema}, {@link GroupPreTranslateAccuracyByTaskSchema}</li>
 * <li>{@code group-translator-accuracy}: {@link GroupTranslatorAccuracySchema}</li>
 * <li>{@code group-saving-activity}: {@link GroupSavingActivitySchema}</li>
 * </ul>
 */
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
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> userIds;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslationCostsPostEditingByTaskSchema extends Schema {
        private Unit unit;
        private Currency currency;
        private ReportsFormat format;
        private BaseRatesForm baseRates;
        private List<IndividualRate> individualRates;
        private NetRateSchemes netRateSchemes;
        private List<Long> taskIds;
        private Boolean useCategoryBasedProofreadRates;
        private Boolean useTmEditDistance;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTopMembersSchema extends Schema {
        private List<Long> projectIds;
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        /**
         * @deprecated not supported by the API for {@code group-top-members}
         */
        @Deprecated
        private GroupingParameter groupBy;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> userIds;
    }

    /**
     * Common Group Task Usage report schema. Use one of the subclasses to get the {@code type} preset.
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsageSchema extends Schema {
        private ReportsFormat format;
        /**
         * Report type: {@code workload}, {@code created-vs-resolved}, {@code performance}, {@code time} or {@code cost}
         */
        private String type;
        private List<Long> projectIds;
        private Date dateFrom;
        private Date dateTo;
        /**
         * Grouping parameter: {@code user} (workload only), {@code language}, {@code type} or {@code project}
         */
        private String groupBy;
        /**
         * Task type: 0 - translate, 1 - proofread, 2 - translate by vendor, 3 - proofread by vendor
         */
        private Integer typeTasks;
        private String languageId;
        private Long creatorId;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsageWorkloadSchema extends GroupTaskUsageSchema {
        private Long assigneeId;

        public GroupTaskUsageWorkloadSchema() {
            setType("workload");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsageCreatedVsResolvedSchema extends GroupTaskUsageSchema {

        public GroupTaskUsageCreatedVsResolvedSchema() {
            setType("created-vs-resolved");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsagePerformanceSchema extends GroupTaskUsageSchema {
        private Long assigneeId;

        public GroupTaskUsagePerformanceSchema() {
            setType("performance");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsageTimeSchema extends GroupTaskUsageSchema {
        private Long assigneeId;
        private Integer wordsCountFrom;
        private Integer wordsCountTo;

        public GroupTaskUsageTimeSchema() {
            setType("time");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTaskUsageCostSchema extends GroupTaskUsageSchema {
        private Long assigneeId;
        /**
         * Task statuses: {@code todo}, {@code in_progress}, {@code done}, {@code closed}, {@code review}
         */
        private List<String> statuses;

        public GroupTaskUsageCostSchema() {
            setType("cost");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupQaCheckIssuesSchema extends Schema {
        private List<Long> projectIds;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslationActivitySchema extends Schema {
        private Unit unit;
        private List<Long> projectIds;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> userIds;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupSourceContentUpdatesSchema extends Schema {
        private Unit unit;
        private ReportsFormat format;
        private List<Long> projectIds;
        private Date dateFrom;
        private Date dateTo;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTimeSpentSchema extends Schema {
        private ReportsFormat format;
        /**
         * Grouping parameter: {@code user} (default), {@code language}, {@code task} or {@code project}
         */
        private String groupBy;
        /**
         * Base rates, only {@code BaseRatesForm#getHourly()} is used
         */
        private BaseRatesForm baseRates;
        /**
         * Individual rates, only languageIds, userIds and {@code IndividualRate#getHourly()} are used
         */
        private List<IndividualRate> individualRates;
        private String languageId;
        private List<Long> userIds;
        /**
         * Task type: 0 - translate, 1 - proofread, 2 - translate by vendor, 3 - proofread by vendor
         */
        private Integer typeTasks;
        private Date dateFrom;
        private Date dateTo;
        private List<Long> projectIds;
        private List<Long> taskIds;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupPreTranslateAccuracySchema extends Schema {
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        /**
         * Split into categories by match score. Ranges should be in descending order (e.g. 100-90, 89-80)
         */
        private List<String> matchScoreCategories;
        private List<Long> projectIds;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupPreTranslateAccuracyByTaskSchema extends Schema {
        private Unit unit;
        private ReportsFormat format;
        /**
         * Split into categories by match score. Ranges should be in descending order (e.g. 100-90, 89-80)
         */
        private List<String> matchScoreCategories;
        private List<Long> taskIds;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupTranslatorAccuracySchema extends Schema {
        private Unit unit;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        /**
         * Split into categories by match score. Ranges should be in descending order (e.g. 100-90, 89-80)
         */
        private List<String> matchScoreCategories;
        private String languageId;
        private List<Long> userIds;
        private List<Long> projectIds;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupSavingActivitySchema extends Schema {
        private Unit unit;
        private List<Long> projectIds;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
        private String languageId;
        /**
         * Report mode: {@code relative} (default) or {@code currency}
         */
        private String mode;
    }
}
