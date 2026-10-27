package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateConceptRequest {

    private String subject;
    private String definition;
    private Boolean translatable;
    private String note;
    private String url;
    private String figure;
    private List<LanguagesDetails> languagesDetails;
    /**
     * Enterprise only. Writes the whole set of custom field values: a field the request omits, or whose value is
     * empty, is cleared.
     */
    private Map<String, Object> fields;

    @Data
    public static class LanguagesDetails {
        private String languageId;
        private String definition;
        private String note;
    }
}
