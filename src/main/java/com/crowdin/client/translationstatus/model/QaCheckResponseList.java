package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QaCheckResponseList {

    private List<QaCheckResponseObject> data;
    private Pagination pagination;

    public static ResponseList<QaCheck> to(QaCheckResponseList qaCheckResponseList) {
        return ResponseList.of(
                qaCheckResponseList.getData().stream()
                        .map(QaCheckResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                qaCheckResponseList.getPagination()
        );
    }
}
