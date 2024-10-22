package com.crowdin.client.ai;

import com.crowdin.client.ai.model.AiProvider;
import com.crowdin.client.ai.model.AiProviderRequest;
import com.crowdin.client.ai.model.Credentials;
import com.crowdin.client.ai.model.AiProviderModel;
import com.crowdin.client.ai.model.AiSetting;
import com.crowdin.client.ai.model.FineTuningDatasetDownload;
import com.crowdin.client.ai.model.FineTuningDatasetRequest;
import com.crowdin.client.ai.model.FineTuningDatasetResponse.FineTuningDatasetData;
import com.crowdin.client.ai.model.FineTuningEvent;
import com.crowdin.client.ai.model.FineTuningJob;
import com.crowdin.client.ai.model.FineTuningJobRequest;
import com.crowdin.client.ai.model.FineTuningJobRequest.Hyperparameters;
import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AIApiTest extends TestClient {
    private static final long userId = 2L;
    private static final long aiPromptId = 3L;
    private static final long progress = 100L;
    private static final int year = 2019;
    private static final int month = Calendar.SEPTEMBER;
    private static final int date = 23;
    private static final int hour = 11;
    private static final int minutes = 26;
    private static final int seconds = 54;
    private static final int limit = 25;
    private static final int offset = 0;
    private static final long size = 1L;
    private static final String status = "finished";
    private static final String jobIdentifier = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private static final TimeZone tz = TimeZone.getTimeZone("GMT");
    private final Calendar calendar = GregorianCalendar.getInstance(tz);


    private static final String FINE_TUNING_DATASET_GENERATION_STATUS_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets/%s";
    private static final String GENERATE_FINE_TUNING_DATASET_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets";
    private static final String GET_FINE_TUNING_EVENT_LIST_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs/%s/events";
    private static final String GET_FINE_TUNING_JOB_LIST_PATH = "%s/users/%d/ai/prompts/fine-tuning/jobs";
    private static final String CREATE_FINE_TUNING_JOB_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs";
    private static final String GET_FINE_TUNING_JOB_STATUS_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs/%s";
    private static final String FINE_TUNING_DATASET_DOWNLOAD_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets/%s/download";
    private static final String GET_SETTINGS = "%s/users/%d/ai/settings";
    private static final String LIST_AI_PROVIDERS = "%s/users/%d/ai/providers";
    private static final String GET_AI_PROVIDER = "%s/users/%d/ai/providers/%d";
    private static final String LIST_AI_MODELS = "%s/users/%d/ai/providers/%d/models";

    private Date getDateTime(int year, int month, int date, int hour, int minute, int second) {
        calendar.set(year, month, date, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format(FINE_TUNING_DATASET_GENERATION_STATUS_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningDatasetGenerationStatus.json"),
            RequestMock.build(String.format(GENERATE_FINE_TUNING_DATASET_PATH, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/generateFineTuningDatasetRequest.json", "api/ai/generateFineTuningDatasetResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_EVENT_LIST_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningEventsResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_JOB_LIST_PATH, this.url, userId), HttpGet.METHOD_NAME, "api/ai/fineTuningJobListResponse.json"),
            RequestMock.build(String.format(CREATE_FINE_TUNING_JOB_PATH, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/fineTuningJobRequest.json", "api/ai/fineTuningJobResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_JOB_STATUS_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningJobStatusResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_DOWNLOAD_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/downloadFineTuningDataset.json"),
            RequestMock.build(String.format(GET_SETTINGS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/getAiSettingResponse.json"),
            RequestMock.build(String.format(GET_SETTINGS, this.url, userId), HttpPatch.METHOD_NAME, "api/ai/editAiSettingRequest.json", "api/ai/getAiSettingResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_DOWNLOAD_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/downloadFineTuningDataset.json"),
            RequestMock.build(String.format(LIST_AI_PROVIDERS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/listAiProviderResponse.json"),
            RequestMock.build(String.format(LIST_AI_PROVIDERS, this.url, userId), HttpPost.METHOD_NAME, "api/ai/addAiProviderRequestObject.json", "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpGet.METHOD_NAME, "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpPatch.METHOD_NAME, "api/ai/editAiProviderRequest.json", "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(LIST_AI_MODELS, this.url, userId, 1), HttpGet.METHOD_NAME,  "api/ai/listAiProviderModels.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpDelete.METHOD_NAME)
        );
    }


    @Test
    public void datasetGenerationStatusTest() {
        Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        final ResponseObject<FineTuningDatasetData> responseObject =
            this.getAiApi().getFineTuningDatasetGenerationStatus(userId, aiPromptId, jobIdentifier);

        final FineTuningDatasetData data = responseObject.getData();
        assertEquals(data.getIdentifier(), jobIdentifier);
        assertEquals(data.getStatus(), status);
        assertEquals(data.getProgress(), progress);
        assertEquals(data.getCreatedAt(), dateCreated);
        assertEquals(data.getStartedAt(), dateCreated);
        assertEquals(data.getUpdatedAt(), dateCreated);
    }

    @Test
    public void generateFineTuningDatasetTest() {
        Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        FineTuningDatasetRequest datasetRequest = new FineTuningDatasetRequest();
        datasetRequest.setTmIds(Collections.singletonList(0L));
        datasetRequest.setProjectIds(Collections.singletonList(0L));
        datasetRequest.setPurpose("training");
        datasetRequest.setMaxExamplesCount(0L);
        datasetRequest.setMaxFileSize(0L);
        datasetRequest.setMinExamplesCount(0L);

        final ResponseObject<FineTuningDatasetData> responseObject =
                this.getAiApi().generateFineTuningDataset(userId, aiPromptId, datasetRequest);

        final FineTuningDatasetData data = responseObject.getData();

        assertEquals(data.getStatus(), status);
        assertEquals(data.getProgress(), progress);
        assertEquals(data.getCreatedAt(), dateCreated);
        assertEquals(data.getStartedAt(), dateCreated);
        assertEquals(data.getUpdatedAt(), dateCreated);
    }

    @Test
    public void getFineTuningEventListTest() {
        ResponseList<FineTuningEvent> responseList = this.getAiApi().getFineTuningEventList(userId, aiPromptId, jobIdentifier);
        Pagination pagination = responseList.getPagination();

        assertEquals(pagination.getLimit(), limit);
        assertEquals(pagination.getOffset(), offset);
        assertEquals(responseList.getData().size(), size);
    }

    @Test
    public void getFineTuningJobListTest() {
        ResponseList<FineTuningJob> responseList = this.getAiApi().getFineTuningJobList(userId, null, null, null, null);
        Pagination pagination = responseList.getPagination();

        assertEquals(pagination.getLimit(), limit);
        assertEquals(pagination.getOffset(), offset);
        assertEquals(responseList.getData().size(), size);
    }

    @Test
    public void createFineTuningJobTest() {
        final Hyperparameters hyperparameters = new Hyperparameters();
        hyperparameters.setBatchSize(0L);
        hyperparameters.setNEpochs(0L);
        hyperparameters.setLearningRateMultiplier(0L);

        final FineTuningJobRequest request = new FineTuningJobRequest();
        request.setDryRun(false);
        request.setHyperparameters(hyperparameters);

        final Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        final ResponseObject<FineTuningJob> responseObject = this.getAiApi().createFineTuningJob(userId, aiPromptId, request);
        assertEquals(responseObject.getData().getIdentifier(), jobIdentifier);
        assertEquals(responseObject.getData().getStatus(), status);
        assertEquals(responseObject.getData().getProgress(), progress);
        assertEquals(responseObject.getData().getFinishedAt(), dateCreated);
        assertEquals(responseObject.getData().getCreatedAt(), dateCreated);
        assertEquals(responseObject.getData().getStartedAt(), dateCreated);
        assertEquals(responseObject.getData().getUpdatedAt(), dateCreated);
    }

    @Test
    public void getFineTuningJobStatusTest() {
        final ResponseObject<FineTuningJob> responseObject = this.getAiApi().getFineTuningJobStatus(userId, aiPromptId, jobIdentifier);
        FineTuningJob job = responseObject.getData();

        final Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        assertEquals(job.getProgress(), progress);
        assertEquals(job.getStatus(), status);
        assertEquals(job.getIdentifier(), jobIdentifier);
        assertEquals(job.getCreatedAt(), dateCreated);
        assertEquals(job.getStartedAt(), dateCreated);
        assertEquals(job.getFinishedAt(), dateCreated);
        assertEquals(job.getUpdatedAt(), dateCreated);
    }

    @Test
    public void downloadFineTuningDatasetTest() {
        ResponseObject<FineTuningDatasetDownload> responseObject = this.getAiApi().downloadFineTuningDataset(userId, aiPromptId, jobIdentifier);
        final Date dateCreated = getDateTime(2019, Calendar.SEPTEMBER, 20, 10, 31, 21);
        assertEquals(responseObject.getData().getExpireIn(), dateCreated);
        assertNotNull(responseObject.getData().getUrl());
    }

    @Test
    public void getAiSettingTest() {
        AiSetting aiSetting = this.getAiApi().getAiSetting(userId).getData();
        assertNotNull(aiSetting);
        assertEquals(aiSetting.getAssistActionAiPromptId(), 2);
        assertEquals(aiSetting.getEditorSuggestionAiPromptId(), 5);
        assertEquals(aiSetting.getShortcuts().size(), 1);
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

    @Test
    public void listAiProvidersTest() {
        ResponseList<AiProvider> response = this.getAiApi().listAiProviders(userId, null, null);
        Pagination pagination = response.getPagination();
        AiProvider aiProvider = response.getData().get(0).getData();
        assertNotNull(response);
        assertEquals(pagination.getOffset(), 0);
        assertEquals(pagination.getLimit(), 25);
        assertEquals(aiProvider.getName(), "OpenAI");
        assertEquals(aiProvider.getType(), "open_ai");
        assertEquals(aiProvider.getCredentials().getApiKey(), "string");
        assertEquals(aiProvider.getIsEnabled(), true);
        assertEquals(aiProvider.getUseSystemCredentials(), false);
        assertEquals(aiProvider.getConfig().getActionRules().get(0).getAction(), "pre_translate");
        assertEquals(aiProvider.getConfig().getActionRules().get(0).getAvailableAiModelIds().get(0), "gpt-3.5-turbo-instruct");
    }

    @Test
    public void addAiProvidersListTest() {
        AiProviderRequest request = new AiProviderRequest();
        final Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        Credentials credentials = new Credentials();
        credentials.setApiKey("string");
        request.setName("OpenAI");
        request.setType("open_ai");
        request.setCredentials(credentials);
        request.setIsEnabled(true);
        request.setUseSystemCredentials(false);
        ResponseObject<AiProvider> response = this.getAiApi().addAiProviders(userId, request);
        assertNotNull(response);
        assertEquals(response.getData().getId(), 2);
        assertEquals(response.getData().getName(), "OpenAI");
        assertEquals(response.getData().getType(), "open_ai");
        assertEquals(response.getData().getCredentials().getApiKey(), "string");
        assertEquals(response.getData().getIsEnabled(), true);
        assertEquals(response.getData().getUseSystemCredentials(), false);
        assertEquals(response.getData().getPromptsCount(), 42);
        assertEquals(response.getData().getCreatedAt(), dateCreated);
        assertEquals(response.getData().getUpdatedAt(), dateCreated);
    }

    @Test
    public void getAiProviderTest() {
        ResponseObject<AiProvider> response = this.getAiApi().getAiProvider(userId, 1);
        final Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        assertNotNull(response);
        assertEquals(response.getData().getId(), 2);
        assertEquals(response.getData().getName(), "OpenAI");
        assertEquals(response.getData().getType(), "open_ai");
        assertEquals(response.getData().getCredentials().getApiKey(), "string");
        assertEquals(response.getData().getIsEnabled(), true);
        assertEquals(response.getData().getUseSystemCredentials(), false);
        assertEquals(response.getData().getPromptsCount(), 42);
        assertEquals(response.getData().getCreatedAt(), dateCreated);
        assertEquals(response.getData().getUpdatedAt(), dateCreated);
    }

    @Test
    public void editAiProviderTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/name");
        List<PatchRequest> patchRequests = new ArrayList<>();
        patchRequests.add(request);
        ResponseObject<AiProvider> response = this.getAiApi().editAiProvider(userId, 1, patchRequests);
        final Date dateCreated = getDateTime(year, month, date, hour, minutes, seconds);
        assertNotNull(response);
        assertEquals(response.getData().getId(), 2);
        assertEquals(response.getData().getName(), "OpenAI");
        assertEquals(response.getData().getType(), "open_ai");
        assertEquals(response.getData().getCredentials().getApiKey(), "string");
        assertEquals(response.getData().getIsEnabled(), true);
        assertEquals(response.getData().getUseSystemCredentials(), false);
        assertEquals(response.getData().getPromptsCount(), 42);
        assertEquals(response.getData().getCreatedAt(), dateCreated);
        assertEquals(response.getData().getUpdatedAt(), dateCreated);
    }

    @Test
    public void deleteAiProviderTest() {
        this.getAiApi().deleteAiProvider(userId, 1);
    }

    @Test
    public void listAiProviderModelsTest() {
        ResponseList<AiProviderModel> response = this.getAiApi().listAiProviderModels(userId, 1);
        AiProviderModel aiProviderModel = response.getData().get(0).getData();
        assertNotNull(aiProviderModel);
        assertEquals(aiProviderModel.getId(), "gpt-3.5-turbo-instruct");
    }
}
