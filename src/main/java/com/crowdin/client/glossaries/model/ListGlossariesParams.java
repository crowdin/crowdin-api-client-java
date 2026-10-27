package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ListGlossariesParams extends Pagination {

    private String orderBy;
    private Long userId;
    private Long groupId;
    /**
     * Filter glossaries by {@code name}
     */
    private String filter;
    private List<OrderByField> orderByList;
}
