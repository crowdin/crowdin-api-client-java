package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

@Data
public class ListGlossariesParams extends Pagination {

    private String orderBy;
    private Long userId;
    private Long groupId;
}
