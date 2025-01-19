package com.crowdin.client.fields.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.fields.model.enums.FieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListFieldsParams extends Pagination {

    private String entity;
    private String search;
    private FieldType type;
}
