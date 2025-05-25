package com.crowdin.client.core.model;

import lombok.Data;

import java.util.Map;

@Data
public class GraphQLRequest {

    private String query;
    private Map<String, Object> variables;
    private String operationName;

    public GraphQLRequest() {
    }

    public GraphQLRequest(String query) {
        this();
        this.query = query;
    }
}
