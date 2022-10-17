package com.crowdin.client.bundles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileResourceResponseList {

    private List<FileResourceResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FileInfoCollectionResourceResponse> to(FileResourceResponseList fileResourceResponseList) {
        return ResponseList.of(
                fileResourceResponseList.getData().stream()
                        .map(FileResourceResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                fileResourceResponseList.getPagination()
        );
    }
}
