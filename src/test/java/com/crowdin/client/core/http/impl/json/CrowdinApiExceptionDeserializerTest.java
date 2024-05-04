package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrowdinApiExceptionDeserializerTest {

    @Test
    void testDeserializeHttpBadRequestException() throws IOException {
        String json = "{\n" +
                "  \"errors\": [\n" +
                "    {\n" +
                "      \"error\": {\n" +
                "        \"key\": \"400\",\n" +
                "        \"errors\": [\n" +
                "          {\n" +
                "            \"code\": \"400\",\n" +
                "            \"message\": \"Bad request exception!\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n"; // Example JSON for HttpBadRequestException
        testDeserialize(json, HttpBadRequestException.class);
    }

    @Test
    void testDeserializeHttpException() throws IOException {
        String json = "{\n" +
                "  \"error\": {\n" +
                "    \"code\": \"400\",\n" +
                "    \"message\": \"Http exception!\"\n" +
                "  }\n" +
                "}\n";
        testDeserialize(json, HttpException.class);
    }

    @Test
    void testDeserializeGenericHttpException() throws IOException {
        String json = "{\"anyOtherField\": \"...\"}"; // Example JSON for HttpException
        testDeserialize(json, HttpException.class);
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
