package com.crowdin.client.vendors.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class VendorResponseList {

    private List<VendorResponseObject> data;
    private Pagination pagination;

    public static ResponseList<Vendor> to(VendorResponseList vendorResponseList) {
        return ResponseList.of(
                vendorResponseList.getData().stream()
                        .map(VendorResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                vendorResponseList.getPagination()
        );
    }
}
