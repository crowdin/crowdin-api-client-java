package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupOptions extends Pagination {

    private Long parentId;
    private Long userId;
    private String orderBy;
}
