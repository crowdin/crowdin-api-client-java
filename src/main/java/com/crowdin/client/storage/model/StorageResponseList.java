package com.crowdin.client.storage.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StorageResponseList {

    private List<StorageResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Storage> to(StorageResponseList storageResponseList) {
        return ResponseList.of(
                storageResponseList.getData().stream()
                        .map(StorageResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                storageResponseList.getPagination()
        );
    }
}
