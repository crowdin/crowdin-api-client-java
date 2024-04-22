package com.crowdin.client.branches.model;

import lombok.Data;

@Data
public class MergeBranchRequest {

    private Boolean deleteAfterMerge;
    private Long sourceBranchId;
    private Boolean dryRun;
}
