package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DirectoryResponseList {

    private List<DirectoryResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Directory> to(DirectoryResponseList directoryResponseList) {
        return ResponseList.of(
                directoryResponseList.getData().stream()
                        .map(DirectoryResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                directoryResponseList.getPagination()
        );
    }
}
