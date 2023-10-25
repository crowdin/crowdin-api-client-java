package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class FileInfo {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;
    private String name;
    private String title;
    private String context;
    private String type;
    private String path;
    private String status;
}
