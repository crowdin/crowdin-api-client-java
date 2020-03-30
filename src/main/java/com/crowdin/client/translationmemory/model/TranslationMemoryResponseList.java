package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TranslationMemoryResponseList {

    private List<TranslationMemoryResponseObject> data;
    private Pagination pagination;

    public static ResponseList<TranslationMemory> to(TranslationMemoryResponseList translationMemoryResponseList) {
        return ResponseList.of(
                translationMemoryResponseList.getData().stream()
                        .map(TranslationMemoryResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                translationMemoryResponseList.getPagination()
        );
    }
}
