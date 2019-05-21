package com.crowdin.common.response;

import java.util.Optional;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public class SimpleResponse<T> implements Response {

    private T data;

    public T getEntity() {
        return data;
    }

    public void doWithEntity(Consumer<? super T> action) {
        action.accept(data);
    }

    public Optional<T> toOptional() {
        return Optional.ofNullable(data);
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getEntity().toString();
    }
}
