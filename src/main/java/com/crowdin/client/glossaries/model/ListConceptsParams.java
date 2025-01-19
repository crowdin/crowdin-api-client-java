package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

@Data
public class ListConceptsParams extends Pagination {

    private String orderBy;
}
