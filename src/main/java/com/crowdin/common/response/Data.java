package com.crowdin.common.response;

class Data<T> {

    private T data;

    T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
