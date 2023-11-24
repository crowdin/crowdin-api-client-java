package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;

@Data
public class AddSourceStringStringsBasedRequest {

    private String text;
    private String identifier;
    private Long branchId;
    private String context;
    private Boolean isHidden;
    private Integer maxLength;
    private List<Long> labelIds;
}
