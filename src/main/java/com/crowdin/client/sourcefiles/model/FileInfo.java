package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Map;

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
    /**
     * Custom fields (Enterprise only)
     */
    private Map<String, Object> fields;
}
