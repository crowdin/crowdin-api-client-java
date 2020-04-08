package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GlossaryResponseList {

    private List<GlossaryResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Glossary> to(GlossaryResponseList glossaryResponseList) {
        return ResponseList.of(
                glossaryResponseList.getData().stream()
                        .map(GlossaryResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                glossaryResponseList.getPagination()
        );
    }
}
