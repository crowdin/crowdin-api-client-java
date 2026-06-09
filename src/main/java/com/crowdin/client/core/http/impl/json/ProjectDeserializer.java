package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProjectDeserializer extends ValueDeserializer<Project> {

    private final ObjectMapper objectMapper;

    public ProjectDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Project deserialize(JsonParser p, DeserializationContext ctxt) {
        JsonNode treeNode = ctxt.readTree(p);
        Iterable<String> iterable = treeNode.propertyNames();
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
