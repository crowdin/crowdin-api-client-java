package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListTeamsParams extends Pagination {
    private String search;
    private List<Long> projectIds;
    private List<ProjectRoles> projectRoles;
    private List<String> languageIs;
    private List<Long> groupIds;
    private List<OrderByField> orderBy;
}
