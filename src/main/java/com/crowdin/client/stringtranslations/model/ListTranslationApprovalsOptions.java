package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListTranslationApprovalsOptions extends Pagination {

    private String orderBy;
    private Long fileId;
    private String labelIds;
    private String excludeLabelIds;
    private Long stringId;
    private String languageId;
    private Long translationId;
    private List<OrderByField> orderByList;
}
