package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpBatchBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CrowdinApiExceptionDeserializer extends ValueDeserializer<CrowdinApiException> {

    private final ObjectMapper objectMapper;

    public CrowdinApiExceptionDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CrowdinApiException deserialize(JsonParser p, DeserializationContext ctxt) {
        JsonNode treeNode = ctxt.readTree(p);
        JsonNode errors = treeNode.get("errors");

        if (errors != null) {
            JsonNode firstElement = errors.get(0);

            if (firstElement != null && firstElement.get("index") != null) {
                return this.objectMapper.treeToValue(treeNode, HttpBatchBadRequestException.class);
            }

            return this.objectMapper.treeToValue(treeNode, HttpBadRequestException.class);
        } else if (treeNode.get("error") != null) {
            return this.objectMapper.treeToValue(treeNode, HttpException.class);
        } else {
            return HttpException.fromMessage(treeNode.toString(), null);
        }
    }
}
