package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AiSetting;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AiApiTest extends TestClient {
    private final Long userId = 1L;
    private final String settingsApi = this.url + "/users/" + userId + "/ai/settings";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(RequestMock.build(settingsApi, HttpGet.METHOD_NAME, "api/ai/getAiSettingResponse.json"),
                RequestMock.build(this.url + "/users/" + userId + "/ai/settings", HttpPatch.METHOD_NAME, "api/ai/editAiSettingRequest.json",
                        "api/ai/getAiSettingResponse.json"));
    }

    @Test
    public void getAiSettingTest() {
        System.out.println("test-url: " + settingsApi);
        ResponseObject<AiSetting> aiSettingResponseObject = this.getAiApi().getAiSetting(userId);
        AiSetting aiSetting = aiSettingResponseObject.getData();
        assertNotNull(aiSetting);
        assertEquals(aiSetting.getAssistActionAiPromptId(), 2);
        assertEquals(aiSetting.getEditorSuggestionAiPromptId(), 5);
        assertEquals(aiSetting.getShortCuts().size(), 1);
    }

    @Test
    public void editAiSettingTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/assistActionAiPromptId");
        ResponseObject<AiSetting> aiSettingResponseObject =
                this.getAiApi().editAiSetting(userId, Collections.singletonList(request));
        assertNotNull(aiSettingResponseObject.getData());
        assertEquals(aiSettingResponseObject.getData().getAssistActionAiPromptId(), 2);
        assertEquals(aiSettingResponseObject.getData().getEditorSuggestionAiPromptId(), 5);
    }
}
