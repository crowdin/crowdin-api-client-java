package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class SourceString {

    private Long id;
    private Long projectId;
    private Long fileId;
    private Long directoryId;
    private Long branchId;
    private Long masterStringId;
    private String identifier;
    private Object text;
    private String type;
    private String context;
    private Integer maxLength;
    private boolean isHidden;
    private Integer revision;
    /**
     * @deprecated deprecated in the API, use {@code type} and {@code text} instead
     */
    @Deprecated
    private boolean hasPlurals;
    /**
     * @deprecated deprecated in the API, use {@code type} instead
     */
    @Deprecated
    private boolean isIcu;
    private List<Long> labelIds;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDuplicate;
    private String webUrl;
    /**
     * Enterprise only. Keys get via List Fields
     */
    private Map<String, Object> fields;
}
