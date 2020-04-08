package com.crowdin.client.glossaries.model;

import lombok.Data;

import java.util.Map;

@Data
public class ImportGlossaryRequest {

    private Long storageId;
    private Map<String, Integer> scheme;
    private boolean firstLineContainsHeader;
}
