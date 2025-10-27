package com.crowdin.client.branches.model;

import lombok.Data;

import java.util.Date;

@Data
public class BranchMergeStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private BranchMergeStatus.Attributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;

    @Data
    public static class Attributes {

        private Integer sourceBranchId;
        private Boolean deleteAfterMerge;
        private Boolean acceptSourceChanges;
    }
}
