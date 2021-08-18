package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.core.http.exceptions.CrowdinApiException;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CrowdinApiExceptionDeserializer extends JsonDeserializer<CrowdinApiException> {

    private final ObjectMapper objectMapper;

    public CrowdinApiExceptionDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public CrowdinApiException deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
        List<String> fields = StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList());
        if (fields.contains("errors")) {
            return this.objectMapper.readValue(treeNode.toString(), HttpBadRequestException.class);
        } else if (fields.contains("error")) {
            return this.objectMapper.readValue(treeNode.toString(), HttpException.class);
        } else {
            return HttpException.fromMessage(treeNode.toString());
        }
    }
}
