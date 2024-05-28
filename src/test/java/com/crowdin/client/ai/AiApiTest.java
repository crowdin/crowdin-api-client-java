package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AddAiPromptRequest;
import com.crowdin.client.ai.model.AddAiProviderRequest;
import com.crowdin.client.ai.model.AiPrompt;
import com.crowdin.client.ai.model.AiPromptResponseObject;
import com.crowdin.client.ai.model.AiProvider;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.Status;
import com.crowdin.client.tasks.model.Task;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AiApiTest extends TestClient {

    private final Long userId = 2L;
    //private final Long projectId = 1L;
    private final Long aiPromptId = 2L;
    private final Long aiProviderId = 14L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/users/" + userId + "/ai/prompts", HttpGet.METHOD_NAME, "api/ai/aiPromptList.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/prompts", HttpPost.METHOD_NAME, "api/ai/addAiPromptRequest.json","api/ai/addAiPromptResponse.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, HttpGet.METHOD_NAME, "api/ai/aiPrompt.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, HttpDelete.METHOD_NAME ),
                RequestMock.build(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, HttpPatch.METHOD_NAME,"api/ai/updateAiPromptRequest.json","api/ai/updateAiPromptResponse.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers", HttpGet.METHOD_NAME, "api/ai/aiProvidersList.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers", HttpPost.METHOD_NAME, "api/ai/addAiProviderRequest.json","api/ai/addAiProviderResponse.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId, HttpGet.METHOD_NAME, "api/ai/aiProvider.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId, HttpPatch.METHOD_NAME, "api/ai/updateAiProviderRequest.json","api/ai/updateAiProviderResponse.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId + "/models", HttpGet.METHOD_NAME, "api/ai/aiModelList.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId + "/chat/completions", HttpGet.METHOD_NAME, "api/ai/aiChatCompletionRequest.json", "api/ai/aiChatCompletionResponse.json")
        );
    }

    @Test
    public void listAiPromptTest(){
        ResponseList<AiPrompt> aiPromptResponseList = this.getAiApi().listAiPrompts(userId, null,null,null, null);
        AiPrompt aiPrompt = aiPromptResponseList.getData().get(0).getData();
        assertEquals(aiPrompt.getName(), "Pre-translate prompt");
    }

    @Test
    public void addAiPromptTest() {
        AddAiPromptRequest addAiPromptRequest = new AddAiPromptRequest();
        addAiPromptRequest.setAction("Pre-translate");
        ResponseObject<AiPrompt> aiPromptResponseObject =this.getAiApi().addAiPrompt(userId,null);
        assertEquals(aiPromptResponseObject.getData().getName(), "Pre-translate prompt");
    }

    @Test
    public void getAiPromptTest() {
        ResponseObject aiPromptResponseObject = this.getAiApi().getAiPrompt(userId, aiPromptId);
        AiPrompt aiPrompt = (AiPrompt)aiPromptResponseObject.getData();
        assertEquals(aiPrompt.getName(),"Pre-translate prompt");
    }

    @Test
    public void deleteAiPromptTest() {
        this.getAiApi().deleteAiPrompt(userId,aiPromptId);
    }

    @Test
    public void listAiProviderTest() {
        ResponseList<AiProvider> aiProviderResponseList = this.getAiApi().listAiProviders(userId, null, null);
        AiProvider aiProvider = aiProviderResponseList.getData().get(0).getData();
        assertEquals(aiProvider.getName(), "OpenAI");
    }

}
