package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AiProvider;
import com.crowdin.client.ai.model.AiProviderModel;
import com.crowdin.client.ai.model.AiProviderModelResponseList;
import com.crowdin.client.ai.model.AiProviderResponseList;
import com.crowdin.client.ai.model.AiProviderRequest;
import com.crowdin.client.ai.model.AiProviderResponseObject;
import com.crowdin.client.ai.model.AiReportGenerate;
import com.crowdin.client.ai.model.AiReportGenerateResponse;
import com.crowdin.client.ai.model.AiSettingResponse;
import com.crowdin.client.ai.model.AiSetting;
import com.crowdin.client.ai.model.FineTuningDatasetDownload;
import com.crowdin.client.ai.model.FineTuningDatasetDownloadResponse;
import com.crowdin.client.ai.model.FineTuningDatasetRequest;
import com.crowdin.client.ai.model.FineTuningDatasetResponse;
import com.crowdin.client.ai.model.FineTuningDatasetResponse.FineTuningDatasetData;
import com.crowdin.client.ai.model.FineTuningEvent;
import com.crowdin.client.ai.model.FineTuningEventResponseList;
import com.crowdin.client.ai.model.FineTuningJob;
import com.crowdin.client.ai.model.FineTuningJobRequest;
import com.crowdin.client.ai.model.FineTuningJobResponseList;
import com.crowdin.client.ai.model.FineTuningJobResponseObject;
import com.crowdin.client.ai.model.GenerateAiReportRequest;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AIApi extends CrowdinApi {

    public AIApi(Credentials credentials) {
        super(credentials);
    }

    public AIApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param jobIdentifier AI prompt fine-tuning dataset generation identifier, consists of 36 characters
     * @return dataset generation status
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.fine-tuning.datasets.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.get" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     * */
    public ResponseObject<FineTuningDatasetData> getFineTuningDatasetGenerationStatus(
        final Long userId, final Long aiPromptId, final String jobIdentifier) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/datasets/" + jobIdentifier);
        FineTuningDatasetResponse response = this.httpClient.get(url, new HttpRequestConfig(), FineTuningDatasetResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param request request body
     * @return fine-tuning dataset
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.post" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<FineTuningDatasetData> generateFineTuningDataset(
        final Long userId, final Long aiPromptId, final FineTuningDatasetRequest request) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/datasets");
        FineTuningDatasetResponse response = this.httpClient.post(url, request, new HttpRequestConfig(), FineTuningDatasetResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param jobIdentifier AI prompt fine-tuning dataset generation identifier, consists of 36 characters
     * @return fine-tuning event list
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.events.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.events.getMany" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseList<FineTuningEvent> getFineTuningEventList(
            final Long userId, final Long aiPromptId, final String jobIdentifier) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/jobs/" +  jobIdentifier + "/events");
        FineTuningEventResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(), FineTuningEventResponseList.class);
        return FineTuningEventResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @return fine-tuning job list
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.getMany" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseList<FineTuningJob> getFineTuningJobList(
            final Long userId, final Integer limit, final Integer offset, final String statuses, final String orderBy) {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "statuses", Optional.ofNullable(statuses),
                "orderBy", Optional.ofNullable(orderBy)
        );
        String url = getAIPath(userId, "ai/prompts/fine-tuning/jobs");
        FineTuningJobResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(queryParams), FineTuningJobResponseList.class);
        return FineTuningJobResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param request request body
     * @return AI Prompt Fine-Tuning Dataset Job
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.post" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<FineTuningJob> createFineTuningJob(final Long userId, final Long aiPromptId, final FineTuningJobRequest request) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/jobs");
        FineTuningJobResponseObject responseObject = this.httpClient.post(url, request, new HttpRequestConfig(), FineTuningJobResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param jobIdentifier AI prompt fine-tuning dataset generation identifier, consists of 36 characters
     * @return AI Prompt Fine-Tuning Dataset Job Status
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.fine-tuning.jobs.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.get" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<FineTuningJob> getFineTuningJobStatus(final Long userId, final Long aiPromptId, final String jobIdentifier) {
        String url = getAIPath(userId, "ai/prompts/" +  aiPromptId + "/fine-tuning/jobs/" + jobIdentifier);
        FineTuningJobResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), FineTuningJobResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param jobIdentifier AI prompt fine-tuning dataset generation identifier, consists of 36 characters
     * @return AI Prompt Fine-Tuning Dataset Download URL
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.fine-tuning.datasets.download.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.download.get" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<FineTuningDatasetDownload> downloadFineTuningDataset(final Long userId, final long aiPromptId, final String jobIdentifier) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/datasets/" + jobIdentifier + "/download");
        FineTuningDatasetDownloadResponse response = this.httpClient.get(url, new HttpRequestConfig(), FineTuningDatasetDownloadResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param request request object
     * @return AI report generation status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.reports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.reports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiReportGenerate> generateAiReport(final Long userId, final GenerateAiReportRequest request) throws HttpException, HttpBadRequestException {
        String url = getAIPath(userId, "ai/reports");
        AiReportGenerateResponse response = this.httpClient.post(url, request, new HttpRequestConfig(), AiReportGenerateResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiReportId AI report identifier, consists of 36 characters
     * @return AI report generation status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.reports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.reports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiReportGenerate> checkAiReportGenerationStatus(final Long userId, final String aiReportId) throws HttpException, HttpBadRequestException {
        String url = getAIPath(userId, String.format("ai/reports/%s", aiReportId));
        AiReportGenerateResponse response = this.httpClient.get(url, new HttpRequestConfig(), AiReportGenerateResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param reportId AI report identifier, consists of 36 characters
     * @return AI report download URL
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.reports.download.download" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.reports.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadAiReport(final Long userId, final String reportId) throws HttpException, HttpBadRequestException {
        String url = getAIPath(userId, String.format("ai/reports/%s/download", reportId));
        DownloadLinkResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @return AI settings
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.get" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<AiSetting> getAiSetting(Long userId) {
        String url = getAIPath(userId, "ai/settings");
        AiSettingResponse aiSettingResponse = this.httpClient.get(url, new HttpRequestConfig(), AiSettingResponse.class);
        return ResponseObject.of(aiSettingResponse.getData());
    }

    /**
     * @param userId user identifier
     * @return updated AI settings
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiSetting> editAiSetting(Long userId, List<PatchRequest> request) {
        String url = getAIPath(userId, "ai/settings");
        AiSettingResponse aiSettingResponse = this.httpClient.patch(url, request, new HttpRequestConfig(), AiSettingResponse.class);
        return ResponseObject.of(aiSettingResponse.getData());
    }

    /**
     * @param userId user identifier
     * @param limit limit
     * @param offset offset
     * @return List of AI Providers
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.providers.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.getMany" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseList<AiProvider> listAiProviders(final Long userId, final Integer limit, final Integer offset) {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        String url = getAIPath(userId, "ai/providers");
        AiProviderResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(queryParams), AiProviderResponseList.class);
        return AiProviderResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @param request AiProviderJobRequest
     * @return AiProvider
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.providers.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.post" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<AiProvider> addAiProviders(final Long userId, final AiProviderRequest request) {
        String url = getAIPath(userId, "ai/providers");
        AiProviderResponseObject response = this.httpClient.post(url, request, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @return AiProvider
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.providers.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.get" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<AiProvider> getAiProvider(final Long userId, final Integer aiProviderId) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId);
        AiProviderResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.providers.delete" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.delete" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public void deleteAiProvider(final Long userId, final Integer aiProviderId) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @param requests list of PatchRequest
     * @return updated AiProvider
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.providers.patch" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.patch" target="_blank"><b>Enterprise API Documentation</b></li>
     * </ul>
     */
    public ResponseObject<AiProvider> editAiProvider(final Long userId, final Integer aiProviderId, List<PatchRequest> requests) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId);
        AiProviderResponseObject response = this.httpClient.patch(url, requests, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @return list of AiProviderModel
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.providers.models.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.models.getMany" target="_blank"><b>Enterprise API Documntation</b></li>
     * </ul>
     */
    public ResponseList<AiProviderModel> listAiProviderModels(final Long userId, final Integer aiProviderId) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId + "/models");
        AiProviderModelResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(), AiProviderModelResponseList.class);
        return AiProviderModelResponseList.to(responseList);
    }

    private String getAIPath(Long userId, String path) {
        return userId != null ? String.format("%s/users/%d/%s", this.url, userId, path) : String.format("%s/%s", this.url, path);
    }

}

