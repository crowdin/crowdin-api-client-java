package com.crowdin.client.screenshots.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListScreenshotsParams extends Pagination {

    private String search;
    private String orderBy;
    private String stringIds;
    private String labelIds;
    private String excludeLabelIds;
    private List<OrderByField> orderByList;
}
