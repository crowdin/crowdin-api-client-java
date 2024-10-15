package com.crowdin.client.ai;

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
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
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


    private String getAIPath(Long userId, String path) {
        return userId != null ? String.format("%s/users/%d/%s", this.url, userId, path) : String.format("%s/%s", this.url, path);
    }

}

