package com.crowdin.client.applications.integrations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class IntegrationLoginFieldResponseList {

    private List<IntegrationLoginField> data;
    private Pagination pagination;

    public static ResponseList<IntegrationLoginField> to(IntegrationLoginFieldResponseList integrationLoginFieldResponseList) {
        return ResponseList.of(
                integrationLoginFieldResponseList.getData().stream()
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                integrationLoginFieldResponseList.getPagination()
        );
    }
}
