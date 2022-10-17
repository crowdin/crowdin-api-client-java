package com.crowdin.client.bundles.model;

import lombok.Data;

@Data
public class FileInfoCollectionResourceResponse {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;
    private String name;
    private String title;
    private String type;
    private String path;
    private String status;
}
