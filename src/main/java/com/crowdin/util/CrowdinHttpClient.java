package com.crowdin.util;

import com.crowdin.common.models.ApiError;
import com.crowdin.common.response.BadRequestResponse;
import com.crowdin.common.response.ErrorResponse;
import com.crowdin.exception.CrowdinApiException;
import com.crowdin.exception.CrowdinException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static com.crowdin.util.ObjectMapperUtil.mapJsonToJavaObject;

public class CrowdinHttpClient {


    public static <I, R> R executeRequest(String uri, I requestBody, TypeReference<R> responseType, HttpMethod method) {
        InputStream responseStream = doRequestAndValidateResponse(uri, requestBody, method).readEntity(InputStream.class);
        return mapJsonToJavaObject(responseStream, responseType);
    }

    public static <I> Response doRequestAndValidateResponse(String uri, I requestBody, HttpMethod method) {
        Response response = doRequest(uri, requestBody, method);
        InputStream stream = response.readEntity(InputStream.class);
        if (ResponseUtil.notSuccess(response.getStatus())) {
            handleApiError(response, stream);
        }

        return response;
    }

    public static InputStream download(String uri) {
        return doRequest(uri, null, HttpMethod.GET)
                .readEntity(InputStream.class);
    }

    private static <I> Response doRequest(String uri, I requestBody, HttpMethod method) {
        return ClientBuilder.newClient()
                // todo remove for test
                .register(HttpAuthenticationFeature.basic("enterprise-tester", "LvGfyutDGcSem8u5aDgQ"))
                .target(getUriFromString(uri))
                .register(MultiPartFeature.class)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .build(method.name(), getEntity(requestBody))
                .invoke();
    }

    private static <I> Entity getEntity(I requestBody) {
        if (requestBody instanceof File) {
            MultiPart multiPart = new MultiPart();
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file",
                    (File) requestBody,
                    MediaType.APPLICATION_OCTET_STREAM_TYPE);
            multiPart.bodyPart(fileDataBodyPart);
            return Entity.entity(multiPart, multiPart.getMediaType());
        }

        return Entity.entity(ObjectMapperUtil.getEntityAsString(requestBody), MediaType.APPLICATION_JSON);
    }

    private static void handleApiError(Response response, InputStream inputStream) {
        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            BadRequestResponse badRequestResponse = mapJsonToJavaObject(inputStream, new TypeReference<BadRequestResponse>() {
            });
            throw new CrowdinApiException(badRequestResponse.toString(), response.getStatus());
        }

        ApiError error = mapJsonToJavaObject(inputStream, new TypeReference<ErrorResponse>() {}).getError();
        throw new CrowdinApiException(error.getMessage(), error.getCode());
    }

    private static URI getUriFromString(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new CrowdinException("Uri is not valid: " + uri);
        }
    }

    public enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE
    }
}