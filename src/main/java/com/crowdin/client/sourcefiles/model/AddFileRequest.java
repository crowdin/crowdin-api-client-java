package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class AddFileRequest {

    private Long storageId;
    private String name;
    private Long branchId;
    private Long directoryId;
    private String title;
    private String type;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
}
