package com.crowdin.client.translations.model;

import lombok.Data;
import java.util.List;
import java.util.Map;
@Data
public class PreTranslationReportResponse {

    private List<Language> languages;
    private String preTranslateType;

    @Data
    public static class Language {
        private String id;
        private List<File> files;
        private Map<String, Integer> skipped;
        private List<String> skippedQaCheckCategories;
    }

    @Data
    public static class File {
        private Long id;
        private Statistics statistics;
    }

    @Data
    public static class Statistics {
        private Integer phrases;
        private Integer words;
    }
}
