package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListOrganizationReportSettingsParams extends Pagination {

    private Long projectId;
    private Long groupId;
}
