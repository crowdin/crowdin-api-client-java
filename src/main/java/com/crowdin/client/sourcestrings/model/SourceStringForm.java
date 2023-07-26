package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;

@Data
public class SourceStringForm {
    private Long fileId;
    private String identifier;
    private Object text;
    private String context;
    private Integer maxLength;
    private Boolean isHidden;
    private List<Long> labelIds;
}
