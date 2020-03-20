package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;

@Data
public class File {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;
    private String name;
    private String title;
    private Type type;
    private Long revisionId;
    private String status;
    private Priority priority;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private Date createdAt;
    private Date updatedAt;
}
