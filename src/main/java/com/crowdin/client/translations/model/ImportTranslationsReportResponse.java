package com.crowdin.client.translations.model;

import com.crowdin.client.core.http.impl.json.EmptyArrayToNullDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class ImportTranslationsReportResponse {

    private List<TargetLanguage> languages;

    @Data
    public static class TargetLanguage {
        private String id;
        private List<File> files;
        @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
        private Map<String, Integer> skipped;
        @JsonDeserialize(using = EmptyArrayToNullDeserializer.class)
        private Map<String, Integer> skippedQaCheckCategories;
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
