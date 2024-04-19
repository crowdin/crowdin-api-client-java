package com.crowdin.client.branches.model;

import lombok.Data;

import java.util.Date;

@Data
public class BranchCloneStatus {

    private String identifier;
    private String status;
    private Integer progress;
    private Object attributes;
    private Date createdAt;
    private Date updatedAt;
    private String startedAt;
    private String finishedAt;
}
