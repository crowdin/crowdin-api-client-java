package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AiProviderModelResponseList {
    private List<AiProviderModelResponseObject> data;
    private Pagination pagination;

    public static ResponseList<AiProviderModel> to(AiProviderModelResponseList responseList) {
        return ResponseList.of(
                responseList.data.stream()
                        .map(AiProviderModelResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
