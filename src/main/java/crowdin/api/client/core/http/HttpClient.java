package crowdin.api.client.core.http;

import crowdin.api.client.core.http.exceptions.HttpBadRequestException;
import crowdin.api.client.core.http.exceptions.HttpException;

public interface HttpClient {

    <T> T get(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T> T delete(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T> T head(String url, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T, V> T post(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T, V> T put(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;

    <T, V> T patch(String url, V data, HttpConfig config, Class<T> clazz) throws HttpException, HttpBadRequestException;
}
