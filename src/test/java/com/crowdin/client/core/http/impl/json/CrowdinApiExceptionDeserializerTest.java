package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrowdinApiExceptionDeserializerTest {

    private String error;
    private String errorsResponse;
    private String unrecognizedError;

    @BeforeEach
    void setUp() throws IOException {
        String resourceDir = "api/core/error.json";
        String errorResponseDir = "api/core/errorsResponse.json";
        String unrecognizedErrorDir = "api/core/unrecognizedError.json";

        error = getFile(resourceDir);
        errorsResponse = getFile(errorResponseDir);
        unrecognizedError = getFile(unrecognizedErrorDir);
    }

    private String getFile(String resourcePath) throws IOException {
        try (InputStream responseInputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (responseInputStream != null) {
                return  new BufferedReader(new InputStreamReader(responseInputStream)).lines().collect(Collectors.joining("\n"));
            } else {
                throw new IOException("File not found: " + resourcePath);
            }

        }
    }
    @Test
    void testDeserializeHttpBadRequestException() throws IOException {
        testDeserialize(errorsResponse, HttpBadRequestException.class);
    }

    @Test
    void testDeserializeHttpException() throws IOException {
        testDeserialize(error, HttpException.class);
    }

    @Test
    void testDeserializeGenericHttpException() throws IOException {
        testDeserialize(unrecognizedError, HttpException.class);
    }

    private void testDeserialize(String json, Class<? extends CrowdinApiException> expectedExceptionClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CrowdinApiExceptionDeserializer deserializer = new CrowdinApiExceptionDeserializer(objectMapper);
        JsonParser parser = new JsonFactory().createParser(json);
        parser.setCodec(objectMapper);

        CrowdinApiException exception = deserializer.deserialize(parser, null);

        assertTrue(expectedExceptionClass.isInstance(exception));
    }
}
