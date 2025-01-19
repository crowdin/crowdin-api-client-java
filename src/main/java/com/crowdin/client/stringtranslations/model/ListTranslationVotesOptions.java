package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListTranslationVotesOptions extends Pagination {

    private Long stringId;
    private String languageId;
    private Long translationId;
    private Long fileId;
    private String labelIds;
    private String excludeLabelIds;
}
