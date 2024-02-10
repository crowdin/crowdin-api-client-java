package com.crowdin.client.translationstatus.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileBranchProgress extends Progress {

    /**
     * For strings-based projects
     */
    private Long branchId;
    /**
     * For non strings-based projects
     */
    private Long fileId;
}
