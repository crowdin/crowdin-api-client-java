package com.crowdin.client.reports.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupTopMembersGenerateGroupReportRequest extends GenerateGroupReportRequest {
    @Setter(AccessLevel.NONE)
    private String name = "group-top-members";
    private Schema schema;

    @Data
    public static class Schema {
        private List<Long> projectIds;
        private Unit unit;
        private String languageId;
        private ReportsFormat format;
        private Date dateFrom;
        private Date dateTo;
    }
}
