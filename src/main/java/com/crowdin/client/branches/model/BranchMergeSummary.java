package com.crowdin.client.branches.model;

import lombok.Data;

import java.util.Map;

@Data
public class BranchMergeSummary {

    private String status;
    private Long sourceBranchId;
    private Long targetBranchId;
    private Boolean dryRun;
    private Map<String, Long> details;
}
