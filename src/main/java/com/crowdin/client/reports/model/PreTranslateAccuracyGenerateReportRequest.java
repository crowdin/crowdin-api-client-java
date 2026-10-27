package com.crowdin.client.reports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PreTranslateAccuracyGenerateReportRequest extends GenerateReportRequest {
    //'pre-translate-efficiency' | 'pre-translate-accuracy'
    private String name;
    private Schema schema;

    @Data
    public static class Schema {
        private Unit unit = Unit.WORDS;
        private ReportsFormat format = ReportsFormat.XLSX;
        /**
         * @deprecated deprecated in the API, use {@link #matchScoreCategories} instead
         */
        @Deprecated
        private List<String> postEditingCategories;
        /**
         * Split into categories by match score. Ranges should be in descending order (e.g. 100-90, 89-80)
         */
        private List<String> matchScoreCategories;
        private Boolean skipArchiving;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GeneralSchema extends Schema {
        private String languageId;
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
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ByTaskSchema extends Schema {
        private Long taskId;
    }

}
