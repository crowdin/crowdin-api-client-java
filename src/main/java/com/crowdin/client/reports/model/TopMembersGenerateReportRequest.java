package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.Format;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TopMembersGenerateReportRequest extends GenerateReportRequest {

    private String name;
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private String languageId;
        private Format format;
    }

}
