package com.crowdin.client.applications.integrations.model;

import lombok.Data;

@Data
public class IntegrationJob {

    private String id;
    private Integer progress;
    private String status;
    private String title;
}
