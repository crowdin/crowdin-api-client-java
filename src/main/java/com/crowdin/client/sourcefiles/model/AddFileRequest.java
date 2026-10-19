package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AddFileRequest {

    private Long storageId;
    private String name;
    private Long branchId;
    private Long directoryId;
    private String title;
    private String context;
    private String type;
    private Integer parserVersion;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<String> excludedTargetLanguages;
    private List<Long> attachLabelIds;
    /**
     * Custom fields (Enterprise only)
     */
    private Map<String, Object> fields;
}
