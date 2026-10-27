package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Task Usage report (Crowdin Enterprise only)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskUsageGenerateReportRequest extends GenerateReportRequest {
    private String name = "task-usage";
    private Schema schema;

    /**
     * Common Task Usage report schema. Use one of the subclasses to get the {@code type} preset.
     */
    @Data
    public static class Schema {
        private ReportsFormat format;
        /**
         * Report type: {@code workload}, {@code created-vs-resolved}, {@code performance}, {@code time} or {@code cost}
         */
        private String type;
        private Date dateFrom;
        private Date dateTo;
        /**
         * Grouping parameter: {@code user} (workload only), {@code language} or {@code type}
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
    public static class WorkloadSchema extends Schema {
        private Long assigneeId;

        public WorkloadSchema() {
            setType("workload");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CreatedVsResolvedSchema extends Schema {

        public CreatedVsResolvedSchema() {
            setType("created-vs-resolved");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class PerformanceSchema extends Schema {
        private Long assigneeId;

        public PerformanceSchema() {
            setType("performance");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class TimeSchema extends Schema {
        private Long assigneeId;
        private Integer wordsCountFrom;
        private Integer wordsCountTo;

        public TimeSchema() {
            setType("time");
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CostSchema extends Schema {
        private Long assigneeId;
        /**
         * Task statuses: {@code todo}, {@code in_progress}, {@code done}, {@code closed}, {@code review}
         */
        private List<String> statuses;

        public CostSchema() {
            setType("cost");
        }
    }
}
