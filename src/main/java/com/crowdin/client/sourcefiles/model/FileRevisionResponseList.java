package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileRevisionResponseList {

    private List<FileRevisionResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FileRevision> to(FileRevisionResponseList fileRevisionResponseList) {
        return ResponseList.of(
                fileRevisionResponseList.getData().stream()
                        .map(FileRevisionResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                fileRevisionResponseList.getPagination()
        );
    }
}
