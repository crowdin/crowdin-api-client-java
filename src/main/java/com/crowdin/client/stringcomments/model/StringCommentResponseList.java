package com.crowdin.client.stringcomments.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StringCommentResponseList {

    private List<StringCommentResponseObject> data;
    private Pagination pagination;

    public static ResponseList<StringComment> to(StringCommentResponseList stringCommentResponseList) {
        return ResponseList.of(
            stringCommentResponseList.getData().stream()
                .map(StringCommentResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            stringCommentResponseList.getPagination()
        );
    }
}