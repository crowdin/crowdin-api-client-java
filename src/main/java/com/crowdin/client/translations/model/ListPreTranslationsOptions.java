package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListPreTranslationsOptions extends Pagination {

    private List<OrderByField> orderByList;
}
