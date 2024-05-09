package com.crowdin.client.reports.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReportArchive {
    private Long id;
    private String scopeType;
    private Long scopeId;
    private Long userId;
    private String name;
    private String webUrl;
    private Object scheme;
    private Date createdAt;
}
