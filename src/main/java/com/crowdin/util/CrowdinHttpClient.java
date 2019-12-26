package com.crowdin.util;

import com.crowdin.common.models.ApiError;
import com.crowdin.common.response.BadRequestResponse;
import com.crowdin.common.response.ErrorResponse;
import com.crowdin.exception.CrowdinApiException;
import com.crowdin.exception.CrowdinException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static com.crowdin.util.ObjectMapperUtil.mapJsonToJavaObject;

public class CrowdinHttpClient {

    public static <R> R executeRequest(RequestWrapper<R> request) {
        InputStream responseStream = doRequestAndValidateResponse(request).readEntity(InputStream.class);
        return mapJsonToJavaObject(responseStream, request.getResponseType());
    }

    public static <I> Response doRequestAndValidateResponse(RequestWrapper<I> request) {
        Response response = doRequest(request);
        InputStream stream = response.readEntity(InputStream.class);
        if (ResponseUtil.notSuccess(response.getStatus())) {
            handleApiError(response, stream);
        }

        return response;
    }

    public static InputStream download(String uri) {
        return ClientBuilder.newClient()
                .target(getUriFromString(uri))
                .request()
                .build(HttpMethod.GET.name(), null)
                .invoke()
                .readEntity(InputStream.class);
    }

    private static <R> Response doRequest(RequestWrapper<R> request) {
        MultivaluedMap<String, Object> headers = Optional
                .ofNullable(request.getHeaders())
                .orElse(new MultivaluedHashMap<>());
        headers.add("Accept", "application/json");

        return ClientBuilder.newClient()
                //todo remove after test
                .register(HttpAuthenticationFeature.basic("enterprise-tester", "LvGfyutDGcSem8u5aDgQ"))
                .target(getUriFromString(request.getUri()))
                .request()
                .headers(headers)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .build(request.getMethod().name(), getEntity(request.getRequestBody()))
                .invoke();
    }

    private static <I> Entity getEntity(I requestBody) {
        if (requestBody instanceof File) {
            try {
                File binary = (File) requestBody;
                FileInputStream entity = new FileInputStream(binary);
                return Entity.entity(entity, getContentType(binary));
            } catch (FileNotFoundException e) {
                throw new CrowdinException(e.getMessage(), e.getCause());
            }
        }

        return Entity.entity(ObjectMapperUtil.getEntityAsString(requestBody), MediaType.APPLICATION_JSON);
    }

    private static void handleApiError(Response response, InputStream inputStream) {
        if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
            BadRequestResponse badRequestResponse = mapJsonToJavaObject(inputStream, new TypeReference<BadRequestResponse>() {
            });
            throw new CrowdinApiException(badRequestResponse.toString(), response.getStatus());
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

    public enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE
    }

    private static String getContentType(File file) {
        String fileExtension = getFileExtension(file);

        if (fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpg")) {
            return "image/jpeg";
        } else if (fileExtension.equalsIgnoreCase(".csv")) {
            return "text/csv";
        } else if (fileExtension.equalsIgnoreCase(".tmx")) {
            return "text/tmx";
        } else if (fileExtension.equalsIgnoreCase(".xliff")) {
            return "application/xliff+xm";
        } else if (fileExtension.equalsIgnoreCase(".xml")) {
            return MediaType.APPLICATION_XML;
        }

        return MediaType.APPLICATION_OCTET_STREAM;
    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    public static class RequestWrapper<R> {
        private String uri;
        private Object requestBody;
        private TypeReference<R> responseType;
        private HttpMethod method;
        private MultivaluedMap<String, Object> headers;

        public RequestWrapper(String uri, Object requestBody, TypeReference<R> responseType, HttpMethod method) {
            this.uri = uri;
            this.requestBody = requestBody;
            this.responseType = responseType;
            this.method = method;
        }

        public RequestWrapper(String uri, Object requestBody, HttpMethod method) {
            this(uri, requestBody, null, method);
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public Object getRequestBody() {
            return requestBody;
        }

        public void setRequestBody(Object requestBody) {
            this.requestBody = requestBody;
        }

        public TypeReference<R> getResponseType() {
            return responseType;
        }

        public void setResponseType(TypeReference<R> responseType) {
            this.responseType = responseType;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public void setMethod(HttpMethod method) {
            this.method = method;
        }

        public MultivaluedMap<String, Object> getHeaders() {
            return headers;
        }

        public void setHeaders(MultivaluedMap<String, Object> headers) {
            this.headers = headers;
        }
    }
}