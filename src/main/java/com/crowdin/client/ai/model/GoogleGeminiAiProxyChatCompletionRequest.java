package com.crowdin.client.ai.model;

import lombok.Data;

@Data
public class GoogleGeminiAiProxyChatCompletionRequest extends AiProxyChatCompletionRequest{
    private String model;
}
