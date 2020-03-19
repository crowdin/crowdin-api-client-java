package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

import java.util.Date;

@Data
public class Directory {

    private Integer id;
    private Integer projectId;
    private Integer branchId;
    private Integer directoryId;
    private String name;
    private String title;
    private String exportPattern;
    private Priority priority;
    private Date createdAt;
    private Date updatedAt;
}
