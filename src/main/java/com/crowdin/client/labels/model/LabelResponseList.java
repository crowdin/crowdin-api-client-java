package com.crowdin.client.labels.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LabelResponseList {

    private List<LabelResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Label> to(LabelResponseList labelResponseList) {
        return ResponseList.of(
            labelResponseList.getData().stream()
                .map(LabelResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            labelResponseList.getPagination()
        );
    }
}
