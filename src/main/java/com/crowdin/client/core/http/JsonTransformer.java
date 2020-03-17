package com.crowdin.client.core.http;

public interface JsonTransformer {

    <T> T parse(String json, Class<T> clazz);

    <T> T parseError(String json, Class<T> clazz);

    <T> String convert(T obj);
}
