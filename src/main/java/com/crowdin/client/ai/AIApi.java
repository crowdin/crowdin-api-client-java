package com.crowdin.client.ai;

import com.crowdin.client.ai.model.*;
import com.crowdin.client.ai.model.FineTuningDatasetResponse.FineTuningDatasetData;
import com.crowdin.client.applications.installations.model.ApplicationInstallationResponseObject;
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
     * @param limit query limit
     * @param offset query offset
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompt.custom.placeholders.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.custom.placeholders.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<CustomPlaceholder> listCustomPlaceholders(Long userId, Integer limit, final Integer offset) {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        String url = getAIPath(userId, "ai/settings/custom-placeholders");
        CustomPlaceholderResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(queryParams), CustomPlaceholderResponseList.class);
        return CustomPlaceholderResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @param request request
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.custom-placeholders.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.custom-placeholders.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<CustomPlaceholder> addCustomPlaceholder(Long userId, CustomPlaceholderRequest request) {
        String url = getAIPath(userId, "ai/settings/custom-placeholders");
        CustomPlaceholderResponseObject response = this.httpClient.post(url, request, new HttpRequestConfig(), CustomPlaceholderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiCustomPlaceholderId aiCustomPlaceholderId identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.custom-placeholders.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.custom-placeholders.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<CustomPlaceholder> getCustomPlaceholder(Long userId, long aiCustomPlaceholderId) {
        String url = getAIPath(userId,"ai/settings/custom-placeholders/" + aiCustomPlaceholderId);
        CustomPlaceholderResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), CustomPlaceholderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiCustomPlaceholderId aiCustomPlaceholderId identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.custom-placeholders.delete" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.custom-placeholders.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteCustomPlaceholder(Long userId, long aiCustomPlaceholderId) {
        String url = getAIPath(userId,"ai/settings/custom-placeholders/" + aiCustomPlaceholderId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiCustomPlaceholderId aiCustomPlaceholderId identifier
     * @param request request
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.settings.custom-placeholders.patch" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.custom-placeholders.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<CustomPlaceholder> editCustomPlaceholder(Long userId, long aiCustomPlaceholderId, List<PatchRequest> request) {
        String url = getAIPath(userId,"ai/settings/custom-placeholders/" + aiCustomPlaceholderId);
        CustomPlaceholderResponseObject response = this.httpClient.patch(url, request, new HttpRequestConfig(), CustomPlaceholderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param jobIdentifier AI prompt fine-tuning dataset generation identifier, consists of 36 characters
     * @return dataset generation status
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.fine-tuning.datasets.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.events.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.jobs.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.fine-tuning.datasets.download.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<FineTuningDatasetDownload> downloadFineTuningDataset(final Long userId, final long aiPromptId, final String jobIdentifier) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/fine-tuning/datasets/" + jobIdentifier + "/download");
        FineTuningDatasetDownloadResponse response = this.httpClient.get(url, new HttpRequestConfig(), FineTuningDatasetDownloadResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId AI prompt identifier
     * @param request request
     * @return AI Prompt Fine-Tuning Dataset Download URL
     * @see <ul>
     *     <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.prompts.clones.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.prompts.clones.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> cloneAiPrompt(final Long userId, final long aiPromptId, final AiPromptCloneRequest request) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/clones");
        AiPromptResponseObject response = this.httpClient.post(url, request, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param projectId project identifier
     * @param action action
     * @param limit query limit
     * @param offset query offset
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.getMany" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<AiPrompt> listAiPrompts(Long userId, Long projectId, String action, Integer limit, final Integer offset) {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId),
                "action", Optional.ofNullable(action),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        String url = getAIPath(userId, "ai/prompts");
        AiPromptResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(queryParams), AiPromptResponseList.class);
        return AiPromptResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @param request request
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.prompts.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> addAiPrompt(Long userId, AiPromptAddRequest request) {
        String url = getAIPath(userId, "ai/prompts");
        AiPromptResponseObject response = this.httpClient.post(url, request, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @param request request
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.ai.prompts.completions.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.completions.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPromptCompletionResponse.AiPromptCompletionData> generatePromptCompletion(Long userId, long aiPromptId, AiPromptCompletionRequest request) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/completions");
        AiPromptCompletionResponse response = this.httpClient.post(url, request, new HttpRequestConfig(), AiPromptCompletionResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @param completionId completion identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.completions.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.completions.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPromptCompletionResponse.AiPromptCompletionData> getPromptCompletionStatus(Long userId, long aiPromptId, String completionId) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/completions/" + completionId);
        AiPromptCompletionResponse response = this.httpClient.get(url, new HttpRequestConfig(), AiPromptCompletionResponse.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @param completionId completion identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.completions.delete" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.completions.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void cancelPromptCompletion(Long userId, long aiPromptId, String completionId) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/completions/" + completionId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @param completionId completion identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.prompts.completions.download.download" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.completions.download.download" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadPromptCompletion(Long userId, long aiPromptId, String completionId) {
        String url = getAIPath(userId, "ai/prompts/" + aiPromptId + "/completions/" + completionId + "/download");
        DownloadLinkResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.get" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.prompts.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> getAiPrompt(Long userId, long aiPromptId) {
        String url = getAIPath(userId,"ai/prompts/" + aiPromptId);
        AiPromptResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), AiPromptResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.delete" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.prompts.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAiPrompt(Long userId, long aiPromptId) {
        String url = getAIPath(userId,"ai/prompts/" + aiPromptId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId user identifier
     * @param aiPromptId aiPrompt identifier
     * @param request request
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.prompts.patch" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.prompts.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiPrompt> editAiPrompt(Long userId, long aiPromptId, List<PatchRequest> request) {
        String url = getAIPath(userId,"ai/prompts/" + aiPromptId);
        AiPromptResponseObject response = this.httpClient.patch(url, request, new HttpRequestConfig(), AiPromptResponseObject.class);
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
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.settings.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiProvider> getAiProvider(final Long userId, final Long aiProviderId) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId);
        AiProviderResponseObject response = this.httpClient.get(url, new HttpRequestConfig(), AiProviderResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @see <ul>
     *     <li><a href="https://support.crowdin.com/developer/api/v2/#tag/AI/operation/api.users.ai.providers.delete" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAiProvider(final Long userId, final Long aiProviderId) {
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<AiProvider> editAiProvider(final Long userId, final Long aiProviderId, List<PatchRequest> requests) {
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
     *     <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/AI/operation/api.ai.providers.models.getMany" target="_blank"><b>Enterprise API Documntation</b></a></li>
     * </ul>
     */
    public ResponseList<AiProviderModel> listAiProviderModels(final Long userId, final Long aiProviderId) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId + "/models");
        AiProviderModelResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(), AiProviderModelResponseList.class);
        return AiProviderModelResponseList.to(responseList);
    }

    /**
     * @param userId user identifier
     * @param aiProviderId id of AiProvider
     * @param request request
     * @return updated AiProvider
     * @see <ul>
     *     <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.ai.providers.chat.completions.post" target="_blank"><b>API Documentation</b></a></li>
     *     <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.ai.providers.chat.completions.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> createProxyChatCompletion(final Long userId, final Long aiProviderId, Map<String, Object> request) {
        String url = getAIPath(userId, "ai/providers/" + aiProviderId + "/chat/completions");
        ChatCompletionResponseObject response = this.httpClient.post(url, request, new HttpRequestConfig(), ChatCompletionResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    private String getAIPath(Long userId, String path) {
        return userId != null ? String.format("%s/users/%d/%s", this.url, userId, path) : String.format("%s/%s", this.url, path);
    }

}

