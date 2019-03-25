package com.crowdin.util;

import com.crowdin.common.models.ApiError;
import com.crowdin.common.response.ErrorResponse;
import com.crowdin.exception.CrowdinApiException;
import com.crowdin.exception.CrowdinBadRequestException;
import com.crowdin.exception.CrowdinException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClient {
    private static ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <I, R> R doRequest(String uri, I requestBody, TypeReference<R> responseType, HttpMethod method) {
        Response response = doRequest(uri, requestBody, method);
        InputStream stream = response.readEntity(InputStream.class);

        if (Response.Status.fromStatusCode(response.getStatus()).getFamily() != Response.Status.Family.SUCCESSFUL) {
            handleApiError(response, stream);
        }

        return mapJsonToJavaObject(stream, responseType);
    }

    private static <I> Response doRequest(String uri, I requestBody, HttpMethod method) {
        return ClientBuilder.newClient()
                .target(getUriFromString(uri))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .build(method.name(), Entity.entity(getEntityAsString(requestBody), MediaType.APPLICATION_JSON))
                .invoke();
    }

    private static <I> String getEntityAsString(I requestBody) {
        try {
            return requestBody != null ? MAPPER.writeValueAsString(requestBody) : null;
        } catch (JsonProcessingException e) {
            throw new CrowdinException("Can`t write entity as string. Message: " + e.getMessage());
        }
    }

    private static void handleApiError(Response response, InputStream inputStream) {
        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            throw new CrowdinBadRequestException();
        }

        ApiError error = mapJsonToJavaObject(inputStream, new TypeReference<ErrorResponse>() {
        }).getError();
        throw new CrowdinApiException(error.getMessage(), error.getCode());
    }

    private static URI getUriFromString(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new CrowdinException("Uri is not valid: " + uri);
        }
    }

    private static <R> R mapJsonToJavaObject(InputStream inputStream, TypeReference<R> responseType) {
        try {
            return MAPPER.readValue(inputStream, responseType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CrowdinException("Can`t deserialize json to class: " + responseType);
        }
    }

    public enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE
    }
}