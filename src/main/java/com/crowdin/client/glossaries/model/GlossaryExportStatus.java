package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GlossaryExportStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;

    @Data
    public static class Attributes {

        private GlossariesFormat format;
        private List<String> exportFields;
        private String text;
        private Boolean caseSensitive;
        private Boolean searchFullMatch;
        private Boolean searchStrict;
        /**
         * Export filter value, e.g. {@code DRAFT}
         */
        private String status;
        /**
         * Export filter value, e.g. {@code VERB}
         */
        private String partOfSpeech;
        /**
         * Export filter value, e.g. {@code ACRONYM}
         */
        private String type;
        /**
         * Export filter value, e.g. {@code OTHER}
         */
        private String gender;
        private Long authorId;
        private Date dateFrom;
        private Date dateTo;
    }
}
