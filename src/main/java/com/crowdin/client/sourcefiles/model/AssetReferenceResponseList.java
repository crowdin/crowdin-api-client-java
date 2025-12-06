package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AssetReferenceResponseList {
    private List<AssetReferenceResponseObject> data;
    private Pagination pagination;

    public static ResponseList<AssetReference> to(AssetReferenceResponseList assetReferenceList) {
        return ResponseList.of(assetReferenceList.getData().stream()
                .map(AssetReferenceResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
        assetReferenceList.getPagination()
        );
    }
}
