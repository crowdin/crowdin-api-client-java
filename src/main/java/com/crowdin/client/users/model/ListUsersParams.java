package com.crowdin.client.users.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.teams.model.ProjectRoles;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListUsersParams extends Pagination {
    private List<OrderByField> orderBy;
    private Status status;
    private String search;
    private TwoFactor twoFactor;
    private List<OrganizationRoles> organizationRoles;
    private Long teamId;
    private List<Long> projectIds;
    private List<ProjectRoles> projectRoles;
    private List<String> languageIds;
    private List<Long> groupIds;
    private Date lastSeenFrom;
    private Date lastSeenTo;
}
