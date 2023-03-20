package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;

import java.util.stream.Collectors;

public class SearchConcordanceResponse {

    public static ResponseList<SearchConcordance> of(SearchConcordanceResponseList searchConcordanceResponse) {
        return ResponseList.of(
                searchConcordanceResponse.getData().stream()
                        .map(SearchConcordanceResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                searchConcordanceResponse.getPagination()
        );
    }

}
