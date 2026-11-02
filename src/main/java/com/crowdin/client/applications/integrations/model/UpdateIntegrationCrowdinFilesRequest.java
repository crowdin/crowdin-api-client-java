package com.crowdin.client.applications.integrations.model;

import lombok.Data;

import java.util.List;

@Data
public class UpdateIntegrationCrowdinFilesRequest {

    private Long projectId;
    private List<IntegrationFile> files;
    private Boolean uploadTranslations;
}
