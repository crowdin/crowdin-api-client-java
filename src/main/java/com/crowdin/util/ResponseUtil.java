package com.crowdin.util;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.core.Response;
import java.io.InputStream;

@SuppressWarnings("WeakerAccess")
public class ResponseUtil {

    public static boolean isSuccess(int statusCode) {
        return Response.Status.fromStatusCode(statusCode).getFamily() == Response.Status.Family.SUCCESSFUL;
    }

    public static boolean notSuccess(int statusCode) {
        return !isSuccess(statusCode);
    }

    public static String getResponceBody(Response response) {
        return ObjectMapperUtil.mapJsonToString(response.readEntity(InputStream.class));
    }

    public static <R> R getResponceBody(Response response, TypeReference<R> responseType) {
        return ObjectMapperUtil.mapJsonToJavaObject(response.readEntity(InputStream.class), responseType);
    }
}
