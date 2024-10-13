package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AiSetting;
import com.crowdin.client.ai.model.AiSettingResponseobject;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseObject;

import java.util.List;

public class AiApi extends CrowdinApi {


    public AiApi(Credentials credentials) {
        super(credentials);
    }

    public AiApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param userId user identifier
     * @return AI settings
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiSetting> getAiSetting(Long userId) throws HttpException, HttpBadRequestException {
        AiSettingResponseobject aiSettingResponseobject = this.httpClient.get(
                this.url + "/users/" + userId + "/ai/settings",
                new HttpRequestConfig(),
                AiSettingResponseobject.class);
        System.out.println("aiSettingResponseobject: " + aiSettingResponseobject);
        return ResponseObject.of(aiSettingResponseobject.getAiSetting());
    }

    /**
     * @param userId user identifier
     * @return updated AI settings
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.patch" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiSetting> editAiSetting(Long userId, List<PatchRequest> request) {
        AiSettingResponseobject aiSettingResponseobject = this.httpClient.patch(
                this.url + "/users/" + userId + "/ai/settings",
                request,
                new HttpRequestConfig(),
                AiSettingResponseobject.class);
        return ResponseObject.of(aiSettingResponseobject.getAiSetting());
    }
}
