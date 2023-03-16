package com.crowdin.client.translationmemory.model;

import com.crowdin.client.core.model.Pagination;
import lombok.Data;

import java.util.List;

@Data
public class SearchConcordanceResponseList {
    private List<SearchConcordanceResponseObject> data;
    private Pagination pagination;
}
