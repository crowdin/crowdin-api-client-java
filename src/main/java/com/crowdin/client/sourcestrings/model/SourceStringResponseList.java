package com.crowdin.client.sourcestrings.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SourceStringResponseList {

    private List<SourceStringResponseObject> data;
    private Pagination pagination;

    public static ResponseList<SourceString> to(SourceStringResponseList sourceStringResponseList) {
        return ResponseList.of(
                sourceStringResponseList.getData().stream()
                        .map(SourceStringResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                sourceStringResponseList.getPagination()
        );
    }
}
