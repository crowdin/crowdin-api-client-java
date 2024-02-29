package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;

@Data
public class AddSourcePluralStringRequest {

    private PluralText text;
    private String identifier;
    private Long fileId;
    private String context;
    private Boolean isHidden;
    private Integer maxLength;
    private List<Long> labelIds;
}
