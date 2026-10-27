package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AiUsageMemberResponseList {
    private List<AiUsageMemberResponseObject> data;
    private Pagination pagination;

    public static ResponseList<AiUsageMember> to(AiUsageMemberResponseList responseList) {
        return ResponseList.of(
                responseList.data.stream()
                        .map(AiUsageMemberResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                responseList.getPagination()
        );
    }
}
