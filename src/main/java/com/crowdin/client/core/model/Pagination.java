package com.crowdin.client.core.model;

import lombok.Data;

@Data
public class Pagination {
    private int offset;
    private int limit;
}
