package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListQaCheckIssuesOptions extends Pagination {

    /**
     * One category or a list of comma-separated ones (e.g. {@code empty,spellcheck})
     */
    private String category;
    /**
     * One validation type or a list of comma-separated ones
     */
    private String validation;
    /**
     * Comma-separated language identifiers
     */
    private String languageIds;
    private Long taskId;
    /**
     * File-based projects only
     */
    private Long fileId;
    /**
     * String-based projects only
     */
    private Long branchId;
}
