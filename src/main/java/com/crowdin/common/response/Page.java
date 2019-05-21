package com.crowdin.common.response;

import com.crowdin.common.models.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Page<T> implements Response {

    private List<Data<T>> data;
    private Pageable pagination;

    public List<T> getContent() {
        if (data == null) return Collections.emptyList();
        return data.stream().map(Data::getData).collect(Collectors.toList());
    }

    public void setData(List<Data<T>> data) {
        this.data = data;
    }

    public Pageable getPagination() {
        return pagination;
    }

    public void setPagination(Pageable pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return getContent().toString();
    }
}
