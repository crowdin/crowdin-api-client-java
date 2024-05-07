package com.crowdin.client.fields.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

@Data
public class FiledPagination extends Pagination {
    private int total;
}
