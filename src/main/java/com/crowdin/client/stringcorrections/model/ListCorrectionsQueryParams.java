package com.crowdin.client.stringcorrections.model;

import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListCorrectionsQueryParams extends Pagination {
    private Long stringId;
    private String orderBy;
    private BooleanInt denormalizePlaceholders;
}
