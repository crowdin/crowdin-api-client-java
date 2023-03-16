package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchConcordance {
    private Glossary glossary;
    private Concept concept;
    private List<Term> sourceTerms;
    private List<Term> targetTerms;
}
