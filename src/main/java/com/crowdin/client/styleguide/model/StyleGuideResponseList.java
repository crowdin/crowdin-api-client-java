package com.crowdin.client.styleguide.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StyleGuideResponseList {
    private List<StyleGuideResponseObject> data;
    private Pagination pagination;

    public static ResponseList<StyleGuide> to(StyleGuideResponseList styleGuideResponseList) {
        return ResponseList.of(
                styleGuideResponseList.getData().stream()
                        .map(StyleGuideResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                styleGuideResponseList.getPagination()
        );
    }
}
