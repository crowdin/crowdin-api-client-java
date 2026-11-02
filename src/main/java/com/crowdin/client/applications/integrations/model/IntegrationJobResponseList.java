package com.crowdin.client.applications.integrations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class IntegrationJobResponseList {

    private List<IntegrationJob> data;
    private Pagination pagination;

    public static ResponseList<IntegrationJob> to(IntegrationJobResponseList integrationJobResponseList) {
        return ResponseList.of(
                integrationJobResponseList.getData().stream()
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                integrationJobResponseList.getPagination()
        );
    }
}
