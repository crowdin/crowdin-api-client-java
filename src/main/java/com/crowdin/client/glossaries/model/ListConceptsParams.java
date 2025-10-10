package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class ListConceptsParams extends Pagination {

    private String orderBy;
    private List<OrderByField> orderByList;
}
