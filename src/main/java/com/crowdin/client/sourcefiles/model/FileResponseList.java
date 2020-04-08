package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileResponseList {

    private List<FileResponseObject> data;
    private Pagination pagination;

    public static ResponseList<File> to(FileResponseList fileResponseList) {
        return ResponseList.of(
                fileResponseList.getData().stream()
                        .map(FileResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                fileResponseList.getPagination()
        );
    }
}
