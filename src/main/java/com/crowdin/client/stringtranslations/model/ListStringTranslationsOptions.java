package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListStringTranslationsOptions extends Pagination {

    private Long stringId;
    private String languageId;
    private String orderBy;
    private BooleanInt denormalizePlaceholders;
    private List<OrderByField> orderByList;
}
