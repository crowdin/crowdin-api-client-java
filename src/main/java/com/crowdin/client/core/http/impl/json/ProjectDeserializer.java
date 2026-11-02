package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
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
        Project project;
        if (fields.contains("languageMapping") || fields.contains("inContext")) {
            project = this.objectMapper.readValue(treeNode.toString(), ProjectSettings.class);
        } else {
            project = this.objectMapper.readValue(treeNode.toString(), Project.class);
        }
        // the accessor-based mapper exposes the primitive "isExternal" field as "external", so map it explicitly
        TreeNode isExternal = treeNode.get("isExternal");
        if (project != null && isExternal != null) {
            JsonToken token = isExternal.asToken();
            if (token == JsonToken.VALUE_TRUE || token == JsonToken.VALUE_FALSE) {
                project.setExternal(token == JsonToken.VALUE_TRUE);
            }
        }
        return project;
    }
}
