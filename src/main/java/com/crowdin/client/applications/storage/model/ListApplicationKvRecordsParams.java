package com.crowdin.client.applications.storage.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListApplicationKvRecordsParams extends Pagination {

    private String prefix;
    private List<OrderByField> orderBy;
}
