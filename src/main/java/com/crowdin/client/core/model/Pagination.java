package com.crowdin.client.core.model;

import lombok.Data;

@Data
public class Pagination {
    private Integer offset;
    private Integer limit;
}
