package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GenerateAiReportRequestSchema {
    private Date dateFrom;
    private Date dateTo;
    private AiReportFormat format;
    private List<Long> projectIds;
    private List<Long> promptIds;
    private List<Long> userIds;
}
