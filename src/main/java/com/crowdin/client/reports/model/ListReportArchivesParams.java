package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListReportArchivesParams extends Pagination {
    private String scopeType;
    private Long scopeId;
    private Long userId;
    private Long taskId;
    private String name;
    /**
     * Archive date from in UTC, ISO 8601
     */
    private String dateFrom;
    /**
     * Archive date to in UTC, ISO 8601
     */
    private String dateTo;
}
