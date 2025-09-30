package com.crowdin.client.core.http.impl.util;

import java.util.Map;
import java.util.stream.Collectors;

import com.crowdin.client.core.model.SortOrder;

public class SortOrderGenerator {
    public static String generateSortParam(Map<String, SortOrder> orderByMap) {
        if (orderByMap == null || orderByMap.isEmpty()) {
            return null;
        }

        String sortParam = orderByMap.entrySet().stream()
                .map(entry -> {
                    if (entry.getValue() == null) {
                        entry.setValue(SortOrder.ASC);
                    }

                    return entry.getKey() + " " + entry.getValue().to(entry.getValue());
                })
                .collect(Collectors.joining(","));

        return RequestEncoder.encodeSpaces(sortParam);


    }
}
