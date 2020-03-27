package com.crowdin.client.screenshots.model;

import lombok.Data;

@Data
public class UpdateScreenshotRequest {

    private Long storageId;
    private String name;
}
