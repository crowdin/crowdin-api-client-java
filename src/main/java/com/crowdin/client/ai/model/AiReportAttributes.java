package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class AiReportAttributes {
    private AiReportFormat format;
    private String reportType;
    private Object schema;
}
