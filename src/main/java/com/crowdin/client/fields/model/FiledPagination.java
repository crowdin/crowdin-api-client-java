package com.crowdin.client.fields.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FiledPagination extends Pagination {
    private int total;
}
