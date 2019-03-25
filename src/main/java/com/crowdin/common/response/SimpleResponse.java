package com.crowdin.common.response;

@SuppressWarnings("WeakerAccess")
public class SimpleResponse<T> implements Response {

    private T data;

    public T getEntity() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getEntity().toString();
    }
}
