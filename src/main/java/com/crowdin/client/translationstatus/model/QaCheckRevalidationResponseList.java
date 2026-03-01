package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QaCheckRevalidationResponseList {

    private List<QaCheckRevalidationResponseObject> data;
    private Pagination pagination;

    public static ResponseList<QaCheckRevalidation> to(QaCheckRevalidationResponseList qaCheckRevalidationResponseList) {
        return ResponseList.of(
                qaCheckRevalidationResponseList.getData().stream()
                        .map(QaCheckRevalidationResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                qaCheckRevalidationResponseList.getPagination()
        );
    }
}
