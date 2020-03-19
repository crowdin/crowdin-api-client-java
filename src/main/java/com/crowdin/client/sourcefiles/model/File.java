package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;

@Data
public class File {

    private Integer id;
    private Integer projectId;
    private Integer branchId;
    private Integer directoryId;
    private String name;
    private String title;
    private Type type;
    private Integer revisionId;
    private String status;
    private Priority priority;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private Date createdAt;
    private Date updatedAt;
}
