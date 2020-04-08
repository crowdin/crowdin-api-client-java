package com.crowdin.client.issues.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class IssueResponseList {

    private List<IssueResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Issue> to(IssueResponseList issueResponseList) {
        return ResponseList.of(
                issueResponseList.getData().stream()
                        .map(IssueResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                issueResponseList.getPagination()
        );
    }
}
