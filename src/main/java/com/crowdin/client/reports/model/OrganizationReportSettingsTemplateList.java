package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrganizationReportSettingsTemplateList {

    private List<OrganizationReportSettingsTemplateResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ReportSettingsTemplate.OrganizationReportSettingsTemplate> to(OrganizationReportSettingsTemplateList reportSettingsTemplateList) {
        return ResponseList.of(
                reportSettingsTemplateList.getData().stream()
                        .map(OrganizationReportSettingsTemplateResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                reportSettingsTemplateList.getPagination()
        );
    }
}
