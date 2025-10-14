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
    private List<OrderByField> orderByList;
}
