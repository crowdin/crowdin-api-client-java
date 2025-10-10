package com.crowdin.client.projectsgroups.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListProjectOptions extends Pagination {

    private Long groupId;
    private Integer hasManagerAccess;
    private String orderBy;
    private Integer type;
    private List<OrderByField> orderByList;
}
