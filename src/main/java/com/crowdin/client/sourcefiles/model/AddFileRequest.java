package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class AddFileRequest {

    private Integer storageId;
    private String name;
    private Integer branchId;
    private Integer directoryId;
    private String title;
    private Type type;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
}
