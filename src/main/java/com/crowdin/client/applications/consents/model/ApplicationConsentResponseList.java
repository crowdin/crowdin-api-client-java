package com.crowdin.client.applications.consents.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApplicationConsentResponseList {

    private List<ApplicationConsentResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ApplicationConsent> to(ApplicationConsentResponseList applicationConsentResponseList) {
        return ResponseList.of(
                applicationConsentResponseList.getData().stream()
                        .map(ApplicationConsentResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                applicationConsentResponseList.getPagination()
        );
    }
}
