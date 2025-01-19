package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListStringTranslationsOptions extends Pagination {

    private Long stringId;
    private String languageId;
    private String orderBy;
    private BooleanInt denormalizePlaceholders;
}
