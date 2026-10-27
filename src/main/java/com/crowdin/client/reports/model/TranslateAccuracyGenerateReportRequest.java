package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TranslateAccuracyGenerateReportRequest extends GenerateReportRequest {
    private String name = "translator-accuracy";
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit;
        private ReportsFormat format;
        /**
         * @deprecated deprecated in the API, use {@link #matchScoreCategories} instead
         */
        @Deprecated
        private List<String> postEditingCategories;
        /**
         * Split into categories by match score. Ranges should be in descending order (e.g. 100-90, 89-80)
         */
        private List<String> matchScoreCategories;
        private String languageId;
        private List<Long> userIds;
        private Date dateFrom;
        private Date dateTo;
        /**
         * File-based projects only
         */
        private List<Long> fileIds;
        /**
         * File-based projects only
         */
        private List<Long> directoryIds;
        private List<Long> branchIds;
        private List<Long> labelIds;
        private LabelIncludeType labelIncludeType;
        private Boolean skipArchiving;
    }

}
