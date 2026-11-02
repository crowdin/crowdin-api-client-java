package com.crowdin.client.applications.storage.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApplicationKvRecordResponseList {

    private List<ApplicationKvRecordResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ApplicationKvRecord> to(ApplicationKvRecordResponseList applicationKvRecordResponseList) {
        return ResponseList.of(
                applicationKvRecordResponseList.getData().stream()
                        .map(ApplicationKvRecordResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                applicationKvRecordResponseList.getPagination()
        );
    }
}
