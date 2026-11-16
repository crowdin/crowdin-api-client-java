package com.crowdin.client.translations.model;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class ImportTranslationsStringsBasedReportResponse {

    private List<TargetLanguage> languages;

    @Data
    public static class TargetLanguage {
        private String id;
        private List<Branch> branches;
        private Map<String, Integer> skipped;
        private Map<String, Integer> skippedQaCheckCategories;
    }

    @Data
    public static class Branch {
        private Long id;
        private Statistics statistics;
    }

    @Data
    public static class Statistics {
        private Integer phrases;
        private Integer words;
    }
}
