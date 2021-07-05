package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;

@Data
public class AddSourceStringRequest {

    private String text;
    private String identifier;
    private Long fileId;
    private String context;
    private Boolean isHidden;
    private Integer maxLength;
    private List<Long> labelIds;
}
