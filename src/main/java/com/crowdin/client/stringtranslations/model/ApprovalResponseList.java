package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApprovalResponseList {

    private List<ApprovalResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Approval> to(ApprovalResponseList approvalResponseList) {
        return ResponseList.of(
                approvalResponseList.getData().stream()
                        .map(ApprovalResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                approvalResponseList.getPagination()
        );
    }
}
