package com.crowdin.client.projectsgroups.model;

import lombok.Data;

import java.util.Date;

@Data
public class Group {

    private Integer id;
    private String name;
    private String description;
    private Integer parentId;
    private Integer organizationId;
    private Integer userId;
    private Integer subgroupsCount;
    private Integer projectsCount;
    private Date createdAt;
    private Date updatedAt;
}
