package com.crowdin.client.screenshots.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListScreenshotsParams extends Pagination {

    private String search;
    private String orderBy;
    private String stringIds;
    private String labelIds;
    private String excludeLabelIds;
}
