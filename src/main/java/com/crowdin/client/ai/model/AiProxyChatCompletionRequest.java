package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class AiProxyChatCompletionRequest {
    private HashMap<String, Object> property;
}
