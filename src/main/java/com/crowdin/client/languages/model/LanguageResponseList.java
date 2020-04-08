package com.crowdin.client.languages.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LanguageResponseList {

    private List<LanguageResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Language> to(LanguageResponseList storageResponseList) {
        return ResponseList.of(
                storageResponseList.getData().stream()
                        .map(LanguageResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                storageResponseList.getPagination()
        );
    }
}
