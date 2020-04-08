package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TermResponseList {

    private List<TermResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Term> to(TermResponseList termResponseList) {
        return ResponseList.of(
                termResponseList.getData().stream()
                        .map(TermResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                termResponseList.getPagination()
        );
    }
}
