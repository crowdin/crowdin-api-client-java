package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListProjectTeamsParams extends Pagination {
    private List<OrderByField> orderBy;
}
