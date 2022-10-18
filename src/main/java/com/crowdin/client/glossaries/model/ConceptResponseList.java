package com.crowdin.client.glossaries.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConceptResponseList {

    private List<ConceptResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Concept> to(ConceptResponseList conceptResponseList) {
        return ResponseList.of(
                conceptResponseList.getData().stream()
                        .map(ConceptResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                conceptResponseList.getPagination()
        );
    }
}
