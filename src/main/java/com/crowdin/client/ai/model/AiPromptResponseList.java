package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AiPromptResponseList {
    private List<AiPromptResponseObject> data;
    private Pagination pagination;

    public static ResponseList<AiPrompt> to(AiPromptResponseList responseList) {
        return ResponseList.of(
            responseList.data.stream()
                .map(AiPromptResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            responseList.pagination
        );
    }
}
