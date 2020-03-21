package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.Date;

@Data
public class SourceString {

    private Long id;
    private Long projectId;
    private Long fileId;
    private String identifier;
    private Object text;
    private String type;
    private String context;
    private Integer maxLength;
    private boolean isHidden;
    private Integer revision;
    private boolean hasPlurals;
    private Object plurals;
    private boolean isIcu;
    private Date createdAt;
    private Date updatedAt;

}
