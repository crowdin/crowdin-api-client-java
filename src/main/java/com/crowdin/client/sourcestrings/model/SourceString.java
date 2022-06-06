package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SourceString {

    private Long id;
    private Long projectId;
    private Long fileId;
    private Long branchId;
    private Long masterStringId;
    private String identifier;
    private Object text;
    private String type;
    private String context;
    private Integer maxLength;
    private boolean isHidden;
    private Integer revision;
    private boolean hasPlurals;
    private boolean isIcu;
    private List<Long> labelIds;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDuplicate;
}
