package com.crowdin.client.users.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Query parameters of List Project Members (Crowdin)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListProjectMembersParams extends Pagination {
    private String search;
    private String role;
    private String languageId;
    private List<OrderByField> orderBy;
}
