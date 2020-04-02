package com.crowdin.client.translationstatus.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LanguageProgressResponseList {

    private List<LanguageProgressResponseObject> data;
    private Pagination pagination;

    public static ResponseList<LanguageProgress> to(LanguageProgressResponseList languageProgressResponseList) {
        return ResponseList.of(
                languageProgressResponseList.getData().stream()
                        .map(LanguageProgressResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                languageProgressResponseList.getPagination()
        );
    }
}
