package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpBatchBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CrowdinApiExceptionDeserializer extends JsonDeserializer<CrowdinApiException> {

    private final ObjectMapper objectMapper;

    public CrowdinApiExceptionDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CrowdinApiException deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.getCodec().readTree(p);
        TreeNode errors = treeNode.get("errors");

        if (errors != null) {
            TreeNode firstElement = errors.get(0);

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
