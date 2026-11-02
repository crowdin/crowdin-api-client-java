package com.crowdin.client.applications.integrations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class IntegrationFileResponseList {

    private List<IntegrationFile> data;
    private Pagination pagination;

    public static ResponseList<IntegrationFile> to(IntegrationFileResponseList integrationFileResponseList) {
        return ResponseList.of(
                integrationFileResponseList.getData().stream()
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                integrationFileResponseList.getPagination()
        );
    }
}
