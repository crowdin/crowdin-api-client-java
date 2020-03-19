package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BranchResponseList {

    private List<BranchResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Branch> to(BranchResponseList branchResponseList) {
        return ResponseList.of(
                branchResponseList.getData().stream()
                        .map(BranchResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                branchResponseList.getPagination()
        );
    }
}
