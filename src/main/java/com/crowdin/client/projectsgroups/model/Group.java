package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;

@Data
public class Group {

    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private Long organizationId;
    private Long userId;
    private Integer subgroupsCount;
    private Integer projectsCount;
    private Date createdAt;
    private Date updatedAt;
}
