package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FileInfoResponseList {

    private List<FileResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FileInfo> to(FileInfoResponseList fileInfoResponseList) {
        return ResponseList.of(
                fileInfoResponseList.getData().stream()
                        .map(FileResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                fileInfoResponseList.getPagination()
        );
    }
}
