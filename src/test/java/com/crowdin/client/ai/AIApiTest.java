package com.crowdin.client.ai;

import com.crowdin.client.ai.model.*;
import com.crowdin.client.ai.model.FineTuningDatasetResponse.FineTuningDatasetData;
import com.crowdin.client.ai.model.FineTuningJobRequest.Hyperparameters;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AIApiTest extends TestClient {
    private static final long userId = 2L;
    private static final long customPlaceholderId = 2L;
    private static final long aiPromptId = 3L;
    private static final long providerId = 0L;
    private static final long progress = 100L;
    private static final int year = 119;
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
    private static final String aiReportId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private static final TimeZone tz = TimeZone.getTimeZone("GMT");
    private static final String completionId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";

    private static final String CUSTOM_PLACEHOLDERS = "%s/users/%d/ai/settings/custom-placeholders";
    private static final String CUSTOM_PLACEHOLDER = "%s/users/%d/ai/settings/custom-placeholders/%s";
    private static final String FINE_TUNING_DATASET_GENERATION_STATUS_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets/%s";
    private static final String GENERATE_FINE_TUNING_DATASET_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets";
    private static final String GET_FINE_TUNING_EVENT_LIST_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs/%s/events";
    private static final String GET_FINE_TUNING_JOB_LIST_PATH = "%s/users/%d/ai/prompts/fine-tuning/jobs";
    private static final String CREATE_FINE_TUNING_JOB_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs";
    private static final String GET_FINE_TUNING_JOB_STATUS_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/jobs/%s";
    private static final String FINE_TUNING_DATASET_DOWNLOAD_PATH = "%s/users/%d/ai/prompts/%d/fine-tuning/datasets/%s/download";
    private static final String GENERATE_AI_REPORT_PATH = "%s/users/%d/ai/reports";
    private static final String CHECK_AI_REPORT_GENERATION_PATH = "%s/users/%d/ai/reports/%s";
    private static final String DOWNLOAD_AI_REPORT_PATH = "%s/users/%d/ai/reports/%s/download";
    private static final String GET_SETTINGS = "%s/users/%d/ai/settings";
    private static final String LIST_AI_PROVIDERS = "%s/users/%d/ai/providers";
    private static final String GET_AI_PROVIDER = "%s/users/%d/ai/providers/%d";
    private static final String LIST_AI_MODELS = "%s/users/%d/ai/providers/%d/models";
    private static final String CLONE_PROMPT = "%s/users/%d/ai/prompts/%d/clones";
    private static final String AI_PROMPTS = "%s/users/%d/ai/prompts";
    private static final String AI_PROMPT = "%s/users/%d/ai/prompts/%d";
    private static final String AI_PROMPT_COMPLETIONS = "%s/users/%d/ai/prompts/%d/completions";
    private static final String AI_PROMPT_COMPLETION = "%s/users/%d/ai/prompts/%d/completions/%s";
    private static final String AI_PROMPT_COMPLETION_DOWNLOAD = "%s/users/%d/ai/prompts/%d/completions/%s/download";
    private static final String PROXY_CHAT = "%s/users/%d/ai/providers/%d/chat/completions";
    private static final String LIST_SUPPORTED_AI_PROVIDER_MODELS = "%s/users/%d/ai/providers/supported-models";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format(CUSTOM_PLACEHOLDERS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/listCustomPlaceholdersResponse.json"),
            RequestMock.build(String.format(CUSTOM_PLACEHOLDERS, this.url, userId), HttpPost.METHOD_NAME, "api/ai/addCustomPlaceholderRequest.json", "api/ai/customPlaceholdersResponse.json"),
            RequestMock.build(String.format(CUSTOM_PLACEHOLDER, this.url, userId, customPlaceholderId), HttpGet.METHOD_NAME, "api/ai/customPlaceholdersResponse.json"),
            RequestMock.build(String.format(CUSTOM_PLACEHOLDER, this.url, userId, customPlaceholderId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format(CUSTOM_PLACEHOLDER, this.url, userId, customPlaceholderId), HttpPatch.METHOD_NAME, "api/ai/editCustomPlaceholderRequest.json", "api/ai/customPlaceholdersResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_GENERATION_STATUS_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningDatasetGenerationStatus.json"),
            RequestMock.build(String.format(GENERATE_FINE_TUNING_DATASET_PATH, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/generateFineTuningDatasetRequest.json", "api/ai/generateFineTuningDatasetResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_EVENT_LIST_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningEventsResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_JOB_LIST_PATH, this.url, userId), HttpGet.METHOD_NAME, "api/ai/fineTuningJobListResponse.json"),
            RequestMock.build(String.format(CREATE_FINE_TUNING_JOB_PATH, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/fineTuningJobRequest.json", "api/ai/fineTuningJobResponse.json"),
            RequestMock.build(String.format(GET_FINE_TUNING_JOB_STATUS_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/fineTuningJobStatusResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_DOWNLOAD_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/downloadFineTuningDataset.json"),
            RequestMock.build(String.format(GENERATE_AI_REPORT_PATH, this.url, userId), HttpPost.METHOD_NAME, "api/ai/generateAiReportRequest.json", "api/ai/generateAiReportResponse.json"),
            RequestMock.build(String.format(CHECK_AI_REPORT_GENERATION_PATH, this.url, userId, aiReportId), HttpGet.METHOD_NAME, "api/ai/checkAiReportGenerationStatusResponse.json"),
            RequestMock.build(String.format(DOWNLOAD_AI_REPORT_PATH, this.url, userId, aiReportId), HttpGet.METHOD_NAME, "api/ai/downloadAiReportResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_DOWNLOAD_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/downloadFineTuningDataset.json"),
            RequestMock.build(String.format(GET_SETTINGS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/getAiSettingResponse.json"),
            RequestMock.build(String.format(GET_SETTINGS, this.url, userId), HttpPatch.METHOD_NAME, "api/ai/editAiSettingRequest.json", "api/ai/getAiSettingResponse.json"),
            RequestMock.build(String.format(FINE_TUNING_DATASET_DOWNLOAD_PATH, this.url, userId, aiPromptId, jobIdentifier), HttpGet.METHOD_NAME, "api/ai/downloadFineTuningDataset.json"),
            RequestMock.build(String.format(LIST_AI_PROVIDERS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/listAiProviderResponse.json"),
            RequestMock.build(String.format(LIST_AI_PROVIDERS, this.url, userId), HttpPost.METHOD_NAME, "api/ai/addAiProviderRequestObject.json", "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpGet.METHOD_NAME, "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpPatch.METHOD_NAME, "api/ai/editAiProviderRequest.json", "api/ai/aiProvidersResponseObject.json"),
            RequestMock.build(String.format(LIST_AI_MODELS, this.url, userId, 1), HttpGet.METHOD_NAME,  "api/ai/listAiProviderModels.json"),
            RequestMock.build(String.format(GET_AI_PROVIDER, this.url, userId, 1), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format(CLONE_PROMPT, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/clonePromptRequest.json", "api/ai/promptResponse.json"),
            RequestMock.build(String.format(AI_PROMPTS, this.url, userId), HttpGet.METHOD_NAME,  "api/ai/listPromptResponse.json"),
            RequestMock.build(String.format(AI_PROMPTS, this.url, userId), HttpPost.METHOD_NAME, "api/ai/addPromptRequest.json", "api/ai/promptResponse.json"),
            RequestMock.build(String.format(AI_PROMPT_COMPLETIONS, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/promptCompletionRequest.json", "api/ai/promptCompletionResponse.json"),
            RequestMock.build(String.format(AI_PROMPT_COMPLETION, this.url, userId, aiPromptId, completionId), HttpGet.METHOD_NAME, "api/ai/promptCompletionResponse.json"),
            RequestMock.build(String.format(AI_PROMPT_COMPLETION, this.url, userId, aiPromptId, completionId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format(AI_PROMPT_COMPLETION_DOWNLOAD, this.url, userId, aiPromptId, completionId), HttpGet.METHOD_NAME, "api/ai/promptCompletionDownload.json"),
            RequestMock.build(String.format(AI_PROMPT, this.url, userId, aiPromptId), HttpGet.METHOD_NAME, "api/ai/promptResponse.json"),
            RequestMock.build(String.format(AI_PROMPT, this.url, userId, aiPromptId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format(AI_PROMPT, this.url, userId, aiPromptId), HttpPatch.METHOD_NAME, "api/ai/editPromptRequest.json", "api/ai/promptResponse.json"),
            RequestMock.build(String.format(PROXY_CHAT, this.url, userId, aiPromptId), HttpPost.METHOD_NAME, "api/ai/proxyChatCompletionRequest.json", "api/ai/proxyChatCompletionResponse.json"),
            RequestMock.build(String.format(LIST_SUPPORTED_AI_PROVIDER_MODELS, this.url, userId), HttpGet.METHOD_NAME, "api/ai/listSupportedAiProviderModels.json")
        );
    }

    @Test
    public void listCustomPlaceholdersTest() {
        final ResponseList<CustomPlaceholder> responseObject =
            this.getAiApi().listCustomPlaceholders(userId, null, null);

        List<ResponseObject<CustomPlaceholder>> data = responseObject.getData();
        assertEquals(data.size(), 1);
        assertEquals(data.get(0).getData().getId(), customPlaceholderId);
        assertEquals(data.get(0).getData().getPlaceholder(), "%custom:productDescription%");
    }

    @Test
    public void addCustomPlaceholderTest() {
        CustomPlaceholderRequest customPlaceholderRequest = new CustomPlaceholderRequest();
        customPlaceholderRequest.setPlaceholder("%custom:productDescription%");
        customPlaceholderRequest.setValue("The product is the professional consulting service that transform challenges into opportunities.");
        customPlaceholderRequest.setDescription("Product description");
        final ResponseObject<CustomPlaceholder> responseObject =
            this.getAiApi().addCustomPlaceholder(userId, customPlaceholderRequest);

        assertEquals(responseObject.getData().getId(), customPlaceholderId);
        assertEquals(responseObject.getData().getPlaceholder(), "%custom:productDescription%");
    }

    @Test
    public void getCustomPlaceholderTest() {
        final ResponseObject<CustomPlaceholder> responseObject =
            this.getAiApi().getCustomPlaceholder(userId, customPlaceholderId);

        assertEquals(responseObject.getData().getId(), customPlaceholderId);
        assertEquals(responseObject.getData().getPlaceholder(), "%custom:productDescription%");
    }

    @Test
    public void deleteCustomPlaceholderTest() {
        this.getAiApi().deleteCustomPlaceholder(userId, customPlaceholderId);
    }

    @Test
    public void editCustomPlaceholderTest() {
        PatchRequest patchRequest = new PatchRequest();
        patchRequest.setOp(PatchOperation.REPLACE);
        patchRequest.setPath("/description");
        final ResponseObject<CustomPlaceholder> responseObject =
                this.getAiApi().editCustomPlaceholder(userId, customPlaceholderId, Collections.singletonList(patchRequest));

        assertEquals(responseObject.getData().getId(), customPlaceholderId);
        assertEquals(responseObject.getData().getPlaceholder(), "%custom:productDescription%");
    }

    @Test
    public void datasetGenerationStatusTest() {
        TimeZone.setDefault(tz);
        Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        final Hyperparameters hyperparameters = new Hyperparameters();
        hyperparameters.setBatchSize(0L);
        hyperparameters.setNEpochs(0L);
        hyperparameters.setLearningRateMultiplier(0L);

        final FineTuningJobRequest request = new FineTuningJobRequest();
        request.setDryRun(false);
        request.setHyperparameters(hyperparameters);

        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        final ResponseObject<FineTuningJob> responseObject = this.getAiApi().getFineTuningJobStatus(userId, aiPromptId, jobIdentifier);
        FineTuningJob job = responseObject.getData();

        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        ResponseObject<FineTuningDatasetDownload> responseObject = this.getAiApi().downloadFineTuningDataset(userId, aiPromptId, jobIdentifier);
        final Date dateCreated = new Date(119, Calendar.SEPTEMBER, 20, 10, 31, 21);
        assertEquals(responseObject.getData().getExpireIn(), dateCreated);
        assertNotNull(responseObject.getData().getUrl());
    }

    @Test
    public void generateAiReportTest() {
        TimeZone.setDefault(tz);
        final Date dateFrom = new Date(year, month, date, hour, minutes, seconds);
        final Date dateTo = new Date(year, month, date, hour, minutes, seconds);

        GenerateAiReportRequestSchema schema = new GenerateAiReportRequestSchema();
        schema.setDateFrom(dateFrom);
        schema.setDateTo(dateTo);
        schema.setFormat(AiReportFormat.JSON);
        schema.setProjectIds(Collections.singletonList(0L));
        schema.setPromptIds(Collections.singletonList(0L));
        schema.setUserIds(Collections.singletonList(userId));

        GenerateAiReportRequest request = new GenerateAiReportRequest();
        request.setType("tokens-usage-raw-data");
        request.setSchema(schema);

        ResponseObject<AiReportGenerate> responseObject = this.getAiApi().generateAiReport(userId, request);
        assertNotNull(responseObject.getData());
        assertEquals(responseObject.getData().getAttributes().getReportType(), request.getType());
    }

    @Test
    public void checkAiReportGenerationStatusTest() {
        TimeZone.setDefault(tz);
        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
        ResponseObject<AiReportGenerate> responseObject = this.getAiApi().checkAiReportGenerationStatus(userId, aiReportId);
        assertNotNull(responseObject.getData());
        assertEquals(responseObject.getData().getCreatedAt(), dateCreated);
    }

    @Test
    public void downloadAiReportTest() {
        ResponseObject<DownloadLink> responseObject = this.getAiApi().downloadAiReport(userId, aiReportId);
        assertNotNull(responseObject.getData());
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
        TimeZone.setDefault(tz);
        AiProviderRequest request = new AiProviderRequest();
        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        ResponseObject<AiProvider> response = this.getAiApi().getAiProvider(userId, 1L);
        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        TimeZone.setDefault(tz);
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/name");
        List<PatchRequest> patchRequests = new ArrayList<>();
        patchRequests.add(request);
        ResponseObject<AiProvider> response = this.getAiApi().editAiProvider(userId, 1L, patchRequests);
        final Date dateCreated = new Date(year, month, date, hour, minutes, seconds);
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
        this.getAiApi().deleteAiProvider(userId, 1L);
    }

    @Test
    public void listAiProviderModelsTest() {
        ResponseList<AiProviderModel> response = this.getAiApi().listAiProviderModels(userId, 1L);
        AiProviderModel aiProviderModel = response.getData().get(0).getData();
        assertNotNull(aiProviderModel);
        assertEquals(aiProviderModel.getId(), "gpt-3.5-turbo-instruct");
    }

    @Test
    public void cloneAiPromptTest() {
        AiPromptCloneRequest request = new AiPromptCloneRequest();
        request.setName("Pre-translate prompt");
        ResponseObject<AiPrompt> aiPromptResponseObject = this.getAiApi().cloneAiPrompt(userId, aiPromptId, request);
        assertEquals(aiPromptResponseObject.getData().getId(), aiPromptId);
        assertEquals(aiPromptResponseObject.getData().getName(), request.getName());
        assertEquals(aiPromptResponseObject.getData().getAction(), "pre_translate");
    }

    @Test
    public void listAiPromptsTest() {
        ResponseList<AiPrompt> aiPromptResponseList = this.getAiApi().listAiPrompts(userId, null, null, null, null);
        assertEquals(aiPromptResponseList.getData().size(), 1);
        assertEquals(aiPromptResponseList.getData().get(0).getData().getId(), aiPromptId);
        assertEquals(aiPromptResponseList.getData().get(0).getData().getName(), "Pre-translate prompt");
        assertEquals(aiPromptResponseList.getData().get(0).getData().getAction(), "pre_translate");
    }

    @Test
    public void addAiPromptTest() {
        AiPromptAddRequest request = new AiPromptAddRequest();
        request.setName("Pre-translate prompt");
        request.setAction("pre_translate");
        request.setConfig(Collections.singletonMap("mode", "basic"));
        ResponseObject<AiPrompt> aiPromptResponseObject = this.getAiApi().addAiPrompt(userId, request);
        assertEquals(aiPromptResponseObject.getData().getId(), aiPromptId);
        assertEquals(aiPromptResponseObject.getData().getName(), request.getName());
        assertEquals(aiPromptResponseObject.getData().getAction(), "pre_translate");
    }

    @Test
    public void generateAiPromptCompletionTest() {
        AiPromptCompletionRequest aiPromptCompletionRequest = new AiPromptCompletionRequest();
        AiPromptCompletionRequest.AiPromptResource aiPromptResource = new AiPromptCompletionRequest.AiPromptResource();
        aiPromptResource.setProjectId(123L);
        aiPromptCompletionRequest.setResources(aiPromptResource);
        ResponseObject<AiPromptCompletionResponse.AiPromptCompletionData> response = this.getAiApi().generatePromptCompletion(userId, aiPromptId, aiPromptCompletionRequest);
        assertEquals(response.getData().getIdentifier(), completionId);
    }

    @Test
    public void getAiPromptCompletionStatusTest() {
        ResponseObject<AiPromptCompletionResponse.AiPromptCompletionData> response = this.getAiApi().getPromptCompletionStatus(userId, aiPromptId, completionId);
        assertEquals(response.getData().getIdentifier(), completionId);
    }

    @Test
    public void cancelAiPromptCompletionTest() {
        this.getAiApi().cancelPromptCompletion(userId, aiPromptId, completionId);
    }

    @Test
    public void downloadAiPromptCompletionTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getAiApi().downloadPromptCompletion(userId, aiPromptId, completionId);
        assertNotNull(downloadLinkResponseObject.getData());
        assertNotNull(downloadLinkResponseObject.getData().getUrl());
        assertFalse(downloadLinkResponseObject.getData().getUrl().isEmpty());
    }

    @Test
    public void getAiPromptTest() {
        ResponseObject<AiPrompt> aiPromptResponseObject = this.getAiApi().getAiPrompt(userId, aiPromptId);
        assertEquals(aiPromptResponseObject.getData().getId(), aiPromptId);
        assertEquals(aiPromptResponseObject.getData().getName(), "Pre-translate prompt");
        assertEquals(aiPromptResponseObject.getData().getAction(), "pre_translate");
    }

    @Test
    public void deleteAiPromptTest() {
        this.getAiApi().deleteAiPrompt(userId, aiPromptId);
    }

    @Test
    public void editAiPromptTest() {
        PatchRequest patchRequest = new PatchRequest();
        patchRequest.setOp(PatchOperation.REPLACE);
        patchRequest.setPath("/name");
        final ResponseObject<AiPrompt> aiPromptResponseObject =
                this.getAiApi().editAiPrompt(userId, aiPromptId, Collections.singletonList(patchRequest));

        assertEquals(aiPromptResponseObject.getData().getId(), aiPromptId);
        assertEquals(aiPromptResponseObject.getData().getName(), "Pre-translate prompt");
        assertEquals(aiPromptResponseObject.getData().getAction(), "pre_translate");
    }


    @Test
    public void createProxyChatCompletionTest() {
        Map<String, Object> req = new HashMap<>();
        req.put("model", "string");
        req.put("stream", true);
        ResponseObject<Map<String, Object>> proxyChatCompletion = this.getAiApi().createProxyChatCompletion(userId, aiPromptId, req);

        assertEquals(proxyChatCompletion.getData().size(), 0);
    }

    @Test
    public void listSupportedAiProviderModelsTest() {
        ResponseList<AiSupportedModel> response = this.getAiApi().listSupportedAiProviderModels(userId, null, null, null, null, null);

        assertEquals(1, response.getData().size());
        assertEquals(providerId, response.getData().get(0).getData().getProviderId());
        assertTrue(response.getData().get(0).getData().getFeatures().getStreaming());
        assertEquals(new Date(year, Calendar.AUGUST, 24, 14, 15, 22), response.getData().get(0).getData().getKnowledgeCutoff());
        assertTrue(response.getData().get(0).getData().getModalities().getInput().getText());
        assertTrue(response.getData().get(0).getData().getModalities().getOutput().getImage());
        assertEquals(0.1, response.getData().get(0).getData().getPrice().getInput());
    }
}
