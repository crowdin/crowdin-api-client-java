package com.crowdin.client.organization.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ExternalQaCheckResponseList {

    private List<ExternalQaCheckResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ExternalQaCheck> to(ExternalQaCheckResponseList externalQaCheckResponseList) {
        return ResponseList.of(
                externalQaCheckResponseList.getData().stream()
                        .map(ExternalQaCheckResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                externalQaCheckResponseList.getPagination()
        );
    }
}
