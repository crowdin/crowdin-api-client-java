package com.crowdin.client.translations.model;

import lombok.Data;
import java.util.List;
import java.util.Map;
import com.crowdin.client.translationstatus.model.Category;


@Data
public class PreTranslationReportResponse {

    private List<TargetLanguage> languages;
    private Method preTranslateType;

    @Data
    public static class TargetLanguage {
        private String id;
        private List<File> files;
        private Map<String, Integer> skipped;
        private List<Category> skippedQaCheckCategories;
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
