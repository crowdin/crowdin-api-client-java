package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class GenerateAiReportRequest {
    private String type;
    private GenerateAiReportRequestSchema schema;
}
