package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;

@Data
public class Directory {

    private Long id;
    private Long projectId;
    private Long branchId;
    private Long directoryId;
    private String name;
    private String title;
    private String exportPattern;
    private String path;
    private Priority priority;
    private Date createdAt;
    private Date updatedAt;
}
