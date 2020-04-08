package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileProgressResponseList {

    private List<FileProgressResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FileProgress> to(FileProgressResponseList fileProgressResponseList) {
        return ResponseList.of(
                fileProgressResponseList.getData().stream()
                        .map(FileProgressResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                fileProgressResponseList.getPagination()
        );
    }
}
