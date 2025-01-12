package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Map;

@Data
public class ChatCompletionResponseObject {
    private Map<String, Object> data;
}
