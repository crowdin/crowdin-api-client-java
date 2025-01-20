package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListLanguageTranslationsOptions extends Pagination {

    private String stringIds;
    private String labelIds;
    private Long fileId;
    private Long branchId;
    private Long directoryId;
    private BooleanInt passedWorkflow;
    private Integer minApprovalCount;
    private String croql;
    private BooleanInt denormalizePlaceholders;
}
