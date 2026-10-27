package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @deprecated this class extends the project report request and cannot be passed to
 * {@link com.crowdin.client.reports.ReportsApi#generateGroupReport(Long, GenerateGroupReportRequest)} or
 * {@link com.crowdin.client.reports.ReportsApi#generateOrganizationReport(GenerateGroupReportRequest)};
 * use {@link GenerateGroupReportRequest} with {@link GenerateGroupReportRequest.GroupTranslationActivitySchema} instead
 */
@Deprecated
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTranslationActivityGenerateReportRequest extends GenerateReportRequest {
    private String name = "group-translation-activity";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private List<Long> projectIds;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
    }
}
