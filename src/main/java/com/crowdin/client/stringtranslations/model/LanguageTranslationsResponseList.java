package com.crowdin.client.stringtranslations.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LanguageTranslationsResponseList {

    private List<LanguageTranslationsResponseObject> data;
    private Pagination pagination;

    public static ResponseList<LanguageTranslations> to(LanguageTranslationsResponseList languageTranslationsResponseList) {
        return ResponseList.of(
            languageTranslationsResponseList.getData().stream()
                .map(LanguageTranslationsResponseObject::getData)
                .map(ResponseObject::of)
                .collect(Collectors.toList()),
            languageTranslationsResponseList.getPagination()
        );
    }
}
