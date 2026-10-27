package com.crowdin.client.reports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @deprecated this class extends the project report request and cannot be passed to
 * {@link com.crowdin.client.reports.ReportsApi#generateGroupReport(Long, GenerateGroupReportRequest)} or
 * {@link com.crowdin.client.reports.ReportsApi#generateOrganizationReport(GenerateGroupReportRequest)};
 * use {@link GenerateGroupReportRequest} with a {@link GenerateGroupReportRequest.GroupTaskUsageSchema} subclass instead
 */
@Deprecated
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTaskUsageGenerateReportRequest extends GenerateReportRequest {
    private String name = "group-task-usage";
    private Schema schema;

    @Data
    public static class Schema {
        private ReportsFormat format;
        private String type;
        private List<Long> projectIds;
        private Date dateFrom;
        private Date dateTo;
        private String groupBy;
        /**
         * @deprecated wrong JSON key, use {@link #typeTasks} instead
         */
        @Deprecated
        @JsonIgnore
        private Integer taskType;
        private Integer typeTasks;
        private String languageId;
        private Integer creatorId;
        private Integer assigneeId;

        /**
         * @deprecated use {@code #getTypeTasks()} instead
         * @return task type
         */
        @Deprecated
        public Integer getTaskType() {
            return this.typeTasks;
        }

        /**
         * @deprecated use {@code #setTypeTasks(Integer)} instead
         * @param taskType task type
         */
        @Deprecated
        public void setTaskType(Integer taskType) {
            this.taskType = taskType;
            this.typeTasks = taskType;
        }
    }
}
