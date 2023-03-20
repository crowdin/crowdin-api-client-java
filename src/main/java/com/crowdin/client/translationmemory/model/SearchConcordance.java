package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Date;

@Data
public class SearchConcordance {
    private TranslationMemory tm;
    private Integer recordId;
    private String source;
    private String target;
    private Integer relevant;
    private String substituted;
    private Date updatedAt;
}
