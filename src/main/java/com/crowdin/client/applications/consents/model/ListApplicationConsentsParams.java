package com.crowdin.client.applications.consents.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListApplicationConsentsParams extends Pagination {
    private String identifier;
    private List<OrderByField> orderBy;
}
