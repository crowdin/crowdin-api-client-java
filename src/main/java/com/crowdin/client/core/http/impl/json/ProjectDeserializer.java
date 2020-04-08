package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProjectDeserializer extends JsonDeserializer<Project> {

    private final ObjectMapper objectMapper;

    public ProjectDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Project deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        Iterable<String> iterable = treeNode::fieldNames;
        List<String> fields = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        if (fields.contains("languageMapping") || fields.contains("inContext")) {
            return this.objectMapper.readValue(treeNode.toString(), ProjectSettings.class);
        } else {
            return this.objectMapper.readValue(treeNode.toString(), Project.class);
        }
    }
}
