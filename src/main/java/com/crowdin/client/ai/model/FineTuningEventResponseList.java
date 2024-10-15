package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class FineTuningEventResponseList {
    private List<FineTuningEventResponseObject> data;
    private Pagination pagination;

    public static ResponseList<FineTuningEvent> to(FineTuningEventResponseList eventResponseList) {
        return ResponseList.of(
            eventResponseList.getData()
                .stream()
                .map(FineTuningEventResponseObject::getData)
                .map(ResponseObject::of).collect(Collectors.toList()),
            eventResponseList.getPagination()
        );
    }
}
