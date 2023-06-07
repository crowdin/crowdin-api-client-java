package com.crowdin.client.screenshots.model;

import lombok.Data;

@Data
public class AddScreenshotRequest {

    private Long storageId;
    private String name;
    private Boolean autoTag;
    private Long fileId;
}
