package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddSourceStringStringsBasedRequest {

    private String text;
    private String identifier;
    private Long branchId;
    private String context;
    private Boolean isHidden;
    private Integer maxLength;
    private List<Long> labelIds;
    /**
     * Enterprise only. Keys get via List Fields
     */
    private Map<String, Object> fields;
}
