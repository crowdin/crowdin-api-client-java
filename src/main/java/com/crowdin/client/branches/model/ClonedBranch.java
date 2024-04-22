package com.crowdin.client.branches.model;

import lombok.Data;

import java.util.Date;

@Data
public class ClonedBranch {

    private Long id;
    private Long projectId;
    private String name;
    private String title;
    private Date createdAt;
    private Date updatedAt;
}
