package com.crowdin.client.translationmemory.model;

import lombok.Data;

import java.util.Date;

@Data
public class TmSegmentRecord {
    private Long id;
    private String languageId;
    private String text;
    private Long usageCount;
    private Long createdBy;
    private Long updatedBy;
    private Date createdAt;
    private Date updatedAt;
}
