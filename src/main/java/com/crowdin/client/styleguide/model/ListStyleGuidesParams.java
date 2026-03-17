package com.crowdin.client.styleguide.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ListStyleGuidesParams extends Pagination {
    private Long userId;
    private String orderBy;
    private List<OrderByField> orderByList;
}
