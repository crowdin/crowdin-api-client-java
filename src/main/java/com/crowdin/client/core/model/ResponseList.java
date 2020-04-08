package com.crowdin.client.core.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseList<T> {

    private List<ResponseObject<T>> data;
    private Pagination pagination;

    public static <T> ResponseList<T> of(List<ResponseObject<T>> data, Pagination pagination) {
        ResponseList<T> responseList = new ResponseList<>();
        responseList.setData(data);
        responseList.setPagination(pagination);
        return responseList;
    }
}
