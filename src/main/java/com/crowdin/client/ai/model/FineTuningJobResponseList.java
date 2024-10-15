package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class FineTuningJobResponseList {
    private List<FineTuningJobResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FineTuningJob> to(FineTuningJobResponseList responseList) {
        return ResponseList.of(
            responseList.data.stream()
                .map(FineTuningJobResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            responseList.pagination
        );
    }
}
