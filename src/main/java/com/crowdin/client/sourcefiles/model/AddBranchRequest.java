package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;

@Data
public class AddBranchRequest {

    private String name;
    private String title;
    private String exportPattern;
    private Priority priority;
    /**
     * String-based projects only
     */
    private Boolean isProtected;
}
