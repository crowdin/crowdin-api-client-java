package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpBatchBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
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
    private String batchErrorsResponse;
    private String unrecognizedError;

    @BeforeEach
    void setUp() throws IOException {
        String resourceDir = "api/core/error.json";
        String errorResponseDir = "api/core/errorsResponse.json";
        String batchErrorsResponseDir = "api/core/batchErrorsResponse.json";
        String unrecognizedErrorDir = "api/core/unrecognizedError.json";

        error = getFile(resourceDir);
        errorsResponse = getFile(errorResponseDir);
        batchErrorsResponse = getFile(batchErrorsResponseDir);
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
    void testDeserializeHttpBatchBadRequestException() throws IOException {
        testDeserialize(batchErrorsResponse, HttpBatchBadRequestException.class);
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
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new SimpleModule().addDeserializer(
                        CrowdinApiException.class, new CrowdinApiExceptionDeserializer(new ObjectMapper())))
                .build();

        CrowdinApiException exception = objectMapper.readValue(json, CrowdinApiException.class);

        assertTrue(expectedExceptionClass.isInstance(exception));
    }
}
