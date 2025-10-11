package com.crowdin.client.sourcestrings.model;

import com.crowdin.client.core.model.OrderByField;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListSourceStringsParams {

    private String orderBy;
    private Integer denormalizePlaceholders;
    private String labelIds;
    private Long fileId;
    private Long branchId;
    private Long directoryId;
    private Long taskId;
    private String croql;
    private String filter;
    private String scope;
    private Integer limit;
    private Integer offset;
    private List<OrderByField> orderByList;
}
