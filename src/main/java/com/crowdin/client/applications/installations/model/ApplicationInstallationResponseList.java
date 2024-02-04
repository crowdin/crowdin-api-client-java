package com.crowdin.client.applications.installations.model;

import com.crowdin.client.applications.model.ApplicationDataResponseObject;
import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.dictionaries.model.Dictionary;
import com.crowdin.client.dictionaries.model.DictionaryResponseList;
import com.crowdin.client.dictionaries.model.DictionaryResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class ApplicationInstallationResponseList {

    private List<ApplicationInstallationResponseObject> data;
    private Pagination pagination;

    public static ResponseList<ApplicationInstallation> to (ApplicationInstallationResponseList applicationInstallationResponseList) {
        return ResponseList.of(
                applicationInstallationResponseList.getData().stream()
                        .map(ApplicationInstallationResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                applicationInstallationResponseList.getPagination()
        );
    }
}
