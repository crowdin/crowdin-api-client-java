package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

@Data
public class AddDirectoryRequest {

    private String name;
    private Long branchId;
    private Long directoryId;
    private String title;
    private String exportPattern;
    private Priority priority;
}
