package com.crowdin.client.translations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PreTranslationResponseList {
    private List<PreTranslationResponseObject> data;
    private Pagination pagination;

    public static ResponseList<PreTranslation> to(PreTranslationResponseList preTranslationResponseList) {
        return ResponseList.of(
                preTranslationResponseList.getData().stream()
                        .map(PreTranslationResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                preTranslationResponseList.getPagination()
        );
    }
}
