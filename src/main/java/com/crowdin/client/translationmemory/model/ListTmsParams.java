package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListTmsParams extends Pagination {

    private String orderBy;
    private List<OrderByField> orderByList;
    /**
     * Filter TMs by {@code name}
     */
    private String filter;
    /**
     * Crowdin only. Project member identifier
     */
    private Long userId;
    /**
     * Enterprise only. Group identifier (set 0 to see TMs of the root group)
     */
    private Long groupId;
}
