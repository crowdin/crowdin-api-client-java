package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiReportGenerate {
    private String identifier;
    private String status;
    private int progress;
    private AiReportAttributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    private String eta;
}
