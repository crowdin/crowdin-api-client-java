package com.crowdin.util;

import com.crowdin.exception.CrowdinException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("WeakerAccess")
public class ObjectMapperUtil {

    static ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <R> R mapJsonToJavaObject(InputStream inputStream, TypeReference<R> responseType) {
        try {
            return MAPPER.readValue(inputStream, responseType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CrowdinException("Can`t deserialize json to class: " + responseType);
        }
    }

    public static String mapJsonToString(InputStream inputStream) {
        return mapJsonToJavaObject(inputStream, new TypeReference<Object>() {
        }).toString();
    }

    public static <I> String getEntityAsString(I requestBody) {
        try {
            return requestBody != null ? MAPPER.writeValueAsString(requestBody) : null;
        } catch (JsonProcessingException e) {
            throw new CrowdinException("Can`t write entity as string. Message: " + e.getMessage());
        }
    }
}
