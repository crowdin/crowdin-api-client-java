package com.crowdin.client.placeholders.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SystemPlaceholderResponseList {

    private List<SystemPlaceholderResponseObject> data;
    private Pagination pagination;

    public static ResponseList<SystemPlaceholder> to(SystemPlaceholderResponseList systemPlaceholderResponseList) {
        return ResponseList.of(
                systemPlaceholderResponseList.getData().stream()
                        .map(SystemPlaceholderResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                systemPlaceholderResponseList.getPagination()
        );
    }
}
