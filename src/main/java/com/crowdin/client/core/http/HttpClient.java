package com.crowdin.client.core.http;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.http.impl.http.ApacheHttpClient;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.EnumConverter;
import com.crowdin.client.translations.model.ProjectBuildResponseList;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Http client interface for library.
 * Default implementation {@link ApacheHttpClient} uses apache http library
 * Develop own implementation if you need to use different http client and pass it to {@link ClientConfig}
 * See {@link HttpClient#post} for documentation
 */
public interface HttpClient {

    <T> T get(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T> T delete(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T> T head(String url, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    /**
     * @param url    base url (with or without query parameters)
     * @param data   request data (can be {@link InputStream} or {@link String} or any other java class which will be converted into json string)
     * @param config http config with query parameters (no need to include them if they are already in {@code url}), headers (without {@code Authorization})
     * @param clazz  return class type (internally http client will work with java classes without generic type, like {@link ProjectBuildResponseList})
     * @param <T>    return type
     * @param <V>    request data type
     * @return http response object
     * @throws HttpException           in case of any error (except validation)
     * @throws HttpBadRequestException in case of validation errors
     */
    <T, V> T post(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T, V> T put(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T, V> T patch(String url, V data, HttpRequestConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    @SuppressWarnings("unchecked")
    default String appendUrlParams(String url, Map<String, ? extends Optional> urlParams) {
        return url + urlParams.entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .map(entry -> {
                    Object value;
                    if (entry.getValue().get() instanceof EnumConverter) {
                        value = ((EnumConverter) entry.getValue().get()).to((Enum) entry.getValue().get());
                    } else {
                        value = entry.getValue().get().toString();
                    }
                    return entry.getKey() + "=" + value.toString();
                })
                .collect(Collectors.joining("&", "?", ""));
    }
}
