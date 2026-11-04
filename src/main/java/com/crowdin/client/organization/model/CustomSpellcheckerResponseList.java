package com.crowdin.client.organization.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomSpellcheckerResponseList {

    private List<CustomSpellcheckerResponseObject> data;
    private Pagination pagination;

    public static ResponseList<CustomSpellchecker> to(CustomSpellcheckerResponseList customSpellcheckerResponseList) {
        return ResponseList.of(
                customSpellcheckerResponseList.getData().stream()
                        .map(CustomSpellcheckerResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                customSpellcheckerResponseList.getPagination()
        );
    }
}
