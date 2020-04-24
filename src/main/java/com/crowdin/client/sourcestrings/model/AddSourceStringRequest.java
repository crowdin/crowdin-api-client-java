package com.crowdin.client.sourcestrings.model;

import lombok.Data;

@Data
public class AddSourceStringRequest {

    private String text;
    private String identifier;
    private Long fileId;
    private String context;
    private Boolean isHidden;
    private Integer maxLength;
}
