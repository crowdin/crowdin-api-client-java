package com.crowdin.client.advisors.model;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdvisorInsightResponseList {

    private List<AdvisorInsightResponseObject> data;
    private AdvisorInsightPagination pagination;

    public static ResponseList<AdvisorInsight> to(AdvisorInsightResponseList advisorInsightResponseList) {
        return ResponseList.of(
                advisorInsightResponseList.getData().stream()
                        .map(AdvisorInsightResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                advisorInsightResponseList.getPagination()
        );
    }
}
