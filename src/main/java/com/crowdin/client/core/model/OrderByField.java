package com.crowdin.client.core.model;

import com.crowdin.client.core.http.impl.util.RequestEncoder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderByField {
    private String fieldName;
    private SortOrder orderBy;

    public static String generateSortParam(List<OrderByField> fields) {
        if (fields == null || fields.isEmpty()) {
            return null;
        }

        String sortParam = fields.stream()
                .map(field -> {
                    if (field.getOrderBy() == null) {
                        field.setOrderBy(SortOrder.ASC);
                    }

                    return field.getFieldName() + " " + field.getOrderBy().to(field.getOrderBy());
                })
                .collect(Collectors.joining(","));

        return RequestEncoder.encodeSpaces(sortParam);
    }
}
