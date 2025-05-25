package com.crowdin.client.core.model;

import lombok.Data;

import java.util.Map;

@Data
public class GraphQLResponse {

    private Map<String, Object> data;
}
