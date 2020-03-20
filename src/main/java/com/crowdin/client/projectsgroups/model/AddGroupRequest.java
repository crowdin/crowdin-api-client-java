package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class AddGroupRequest {

    private String name;
    private Long parentId;
    private String description;
}
