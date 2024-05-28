package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AddAiPromptRequest;
import com.crowdin.client.ai.model.AddAiProviderRequest;
import com.crowdin.client.ai.model.AiPrompt;
import com.crowdin.client.ai.model.AiPromptResponseList;
import com.crowdin.client.ai.model.AiPromptResponseObject;
import com.crowdin.client.ai.model.AiProvider;
import com.crowdin.client.ai.model.AiProviderModel;
import com.crowdin.client.ai.model.AiProviderModelResponseList;
import com.crowdin.client.ai.model.AiProviderResponseList;
import com.crowdin.client.ai.model.AiProviderResponseObject;
import com.crowdin.client.ai.model.AiProxyChatCompletionRequest;
import com.crowdin.client.ai.model.AiProxyChatCompletionResponse;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AiApi extends CrowdinApi {

    public AiApi(Credentials credentials) {
        super(credentials);
    }

    public AiApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param userId User identifier
     * @return list of Ai Prompts
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.ai.prompts.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<AiPrompt> listAiPrompts (Long userId, Long projectId, String action, Long limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "action", Optional.ofNullable(action),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        AiPromptResponseList aiPromptResponseList = this.httpClient.get(this.url + "/users/" + userId + "/ai/prompts", new HttpRequestConfig(queryParams), AiPromptResponseList.class);
        return AiPromptResponseList.to(aiPromptResponseList);
    }

    /**
     * @param userId user identifier
     * @param request request object
     * @return newly created Ai prompt
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.prompts.post" target="_blank"><b>API Documentation</b></a></li>
    * </ul>
     */
    public ResponseObject<AiPrompt> addAiPrompt (Long userId, AddAiPromptRequest request) throws HttpException, HttpBadRequestException {
        AiPromptResponseObject aiPromptResponseObject = this.httpClient.post(this.url + "/users/" + userId + "/ai/prompts", request, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(aiPromptResponseObject.getData());
    }

    /**
     * @param userId User identifier
     * @return list of Ai Prompts
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.ai.prompts.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> getAiPrompt (Long userId, Long aiPromptId) throws HttpException, HttpBadRequestException {
        AiPromptResponseObject response = this.httpClient.get(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(response.getData());
    }


    /**
     * @param userId user identifier
     * @param aiPromptId ai prompt identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.prompts.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAiPrompt (Long userId, Long aiPromptId) throws HttpException, HttpBadRequestException {
         this.httpClient.delete(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiPromptId ai prompt identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.prompts.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> editAiPrompt (Long userId, Long aiPromptId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        AiPromptResponseObject response = this.httpClient.patch(this.url + "/users/" + userId + "/ai/prompts/" + aiPromptId, request, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId User identifier
     * @return list of Ai Providers
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.ai.providers.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<AiProvider> listAiProviders (Long userId, Long limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        AiProviderResponseList response = this.httpClient.get(this.url + "/users/" + userId + "/ai/providers", new HttpRequestConfig(queryParams), AiProviderResponseList.class);
        return AiProviderResponseList.to(response);
    }

    /**
     * @param userId user identifier
     * @return newly created Ai Provider
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.providers.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiProvider> addAiProvider (Long userId, AddAiProviderRequest request) throws HttpException, HttpBadRequestException {
        AiProviderResponseObject aiProviderResponseObject = this.httpClient.post(this.url + "/users/" + userId + "/ai/providers", request, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(aiProviderResponseObject.getData());
    }

    /**
     * @param userId User identifier
     * @param aiProviderId Ai Provider Identifier
     * @return list of Ai Prompts
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.providers.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiProvider> getAiProvider (Long userId, Long aiProviderId) throws HttpException, HttpBadRequestException {
        AiProviderResponseObject response = this.httpClient.get(this.url + "/users/" + userId + "/ai/providers" + aiProviderId, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiProviderId Ai Provider Identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.providers.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAiProvider (Long userId, Long aiProviderId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiProviderId ai prompt identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.prompts.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiProvider> editAiProvider (Long userId, Long aiProviderId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        AiProviderResponseObject response = this.httpClient.patch(this.url + "/users/" + userId + "/ai/providers/" + aiProviderId, request, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }


    /**
     * @param userId User identifier
     * @param aiProviderId ai prompt identifier
     * @return list of Ai Provider Models
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.ai.providers.models.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<AiProviderModel> listAiProviderModels (Long userId, Long aiProviderId) throws HttpException, HttpBadRequestException {
        AiProviderModelResponseList response = this.httpClient.get(this.url + "/users/" + userId + "/ai/providers" + aiProviderId + "/models", new HttpRequestConfig(), AiProviderModelResponseList.class);
        return AiProviderModelResponseList.to(response);
    }

    /**
     * @param userId user identifier
     * @param aiProviderId ai prompt identifier
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.providers.chat.completions.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public AiProxyChatCompletionResponse createAiProxyChatCompletion (Long userId, Long aiProviderId, AiProxyChatCompletionRequest request) throws HttpException, HttpBadRequestException {
        AiProxyChatCompletionResponse aiProxyChatCompletionResponse = this.httpClient.post(this.url + "/users/" + userId + "/ai/providers" + aiProviderId + "/chat/completions", request, new HttpRequestConfig(), AiProxyChatCompletionResponse.class);
        return aiProxyChatCompletionResponse;
    }
}
