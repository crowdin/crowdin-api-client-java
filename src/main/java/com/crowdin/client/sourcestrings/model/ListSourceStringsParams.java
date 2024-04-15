package com.crowdin.client.sourcestrings.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListSourceStringsParams {

    private String orderBy;
    private Integer denormalizePlaceholders;
    private String labelIds;
    private Long fileId;
    private Long branchId;
    private Long directoryId;
    private String croql;
    private String filter;
    private String scope;
    private Integer limit;
    private Integer offset;
}
