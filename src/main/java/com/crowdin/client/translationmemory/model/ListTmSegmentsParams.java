package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListTmSegmentsParams extends Pagination {

    private String orderBy;
    private List<OrderByField> orderByList;
    /**
     * Filter segments by <a href="https://developer.crowdin.com/croql/">CroQL</a>
     */
    private String croql;
}
