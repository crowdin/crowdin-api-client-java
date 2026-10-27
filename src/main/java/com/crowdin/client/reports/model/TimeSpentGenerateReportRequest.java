package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeSpentGenerateReportRequest extends GenerateReportRequest {
    private String name = "time-spent";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;
        /**
         * Grouping parameter: {@code user} (default), {@code language} or {@code task}
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
        private List<Long> taskIds;
        /**
         * Crowdin Enterprise only
         */
        private Long workflowStepId;
        private Boolean skipArchiving;
    }
}
