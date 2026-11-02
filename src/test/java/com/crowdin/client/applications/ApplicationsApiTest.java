package com.crowdin.client.applications;

import com.crowdin.client.applications.consents.model.*;
import com.crowdin.client.applications.installations.model.*;
import com.crowdin.client.applications.installations.model.Module;
import com.crowdin.client.applications.integrations.model.*;
import com.crowdin.client.applications.storage.model.*;
import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.SortOrder;
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
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationsApiTest extends TestClient {

    private final String applicationIdentifier = "identifier";
    private final String path = "path";
    private final String builtUrl = this.url + "/applications/" + applicationIdentifier + "/api/" + path;
    private final String builtUrlInstallation = this.url + "/applications/installations";
    private final String builtUrlConsents = this.url + "/applications/consents";
    private final Long consentId = 12L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(builtUrl, HttpGet.METHOD_NAME, "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPut.METHOD_NAME, "api/applications/updateOrRestoreApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPost.METHOD_NAME, "api/applications/addApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpDelete.METHOD_NAME),
                RequestMock.build(builtUrl, HttpPatch.METHOD_NAME, "api/applications/editApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpGet.METHOD_NAME, "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpDelete.METHOD_NAME),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpPatch.METHOD_NAME, "api/applications/editApplicationInstallation.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation, HttpGet.METHOD_NAME, "api/applications/installApplicationList.json"),
                RequestMock.build(builtUrlInstallation, HttpPost.METHOD_NAME, "api/applications/installApplication.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlConsents, HttpGet.METHOD_NAME, "api/applications/listApplicationConsentsResponse.json"),
                RequestMock.build(builtUrlConsents, HttpPost.METHOD_NAME, "api/applications/addApplicationConsentRequest.json", "api/applications/applicationConsent.json"),
                RequestMock.build(builtUrlConsents + "/" + consentId, HttpPatch.METHOD_NAME, "api/applications/editApplicationConsentRequest.json", "api/applications/applicationConsent.json"),
                RequestMock.build(builtUrlConsents + "/" + consentId, HttpDelete.METHOD_NAME),
                // generic application data with query params
                RequestMock.build(builtUrl, HttpGet.METHOD_NAME, "api/applications/applicationData.json", dataQueryParams()),
                RequestMock.build(builtUrl, HttpDelete.METHOD_NAME, null, dataQueryParams()),
                // installations
                RequestMock.build(builtUrlInstallation, HttpGet.METHOD_NAME, "api/applications/installApplicationList.json", installationsQueryParams()),
                RequestMock.build(builtUrlInstallation, HttpPost.METHOD_NAME, "api/applications/installApplicationWithModules.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation, HttpPost.METHOD_NAME, "api/applications/installApplicationFromManifest.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier + "/bundles", HttpPost.METHOD_NAME, "api/applications/uploadApplicationBundleRequest.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier + "/update", HttpGet.METHOD_NAME, "api/applications/applicationInstallationUpdate.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier + "/update", HttpPost.METHOD_NAME, "api/applications/applyApplicationInstallationUpdateRequest.json", "api/applications/applicationInstallation.json"),
                // KV storage
                RequestMock.build(builtUrlKv, HttpGet.METHOD_NAME, "api/applications/listApplicationKvRecords.json", kvQueryParams()),
                RequestMock.build(builtUrlKv, HttpPost.METHOD_NAME, "api/applications/addApplicationKvRecordRequest.json", "api/applications/applicationKvRecord.json"),
                RequestMock.build(builtUrlKv + "/" + encodedKvKey, HttpGet.METHOD_NAME, "api/applications/applicationKvRecord.json"),
                RequestMock.build(builtUrlKv + "/" + encodedKvKey, HttpPatch.METHOD_NAME, "api/applications/editApplicationKvRecordRequest.json", "api/applications/applicationKvRecord.json"),
                RequestMock.build(builtUrlKv + "/" + encodedKvKey, HttpDelete.METHOD_NAME),
                // integrations
                RequestMock.build(builtUrlIntegration + "/crowdin-files", HttpGet.METHOD_NAME, "api/applications/integrationCrowdinFiles.json", projectQueryParams()),
                RequestMock.build(builtUrlIntegration + "/integration-files", HttpGet.METHOD_NAME, "api/applications/integrationFiles.json", projectQueryParams()),
                RequestMock.build(builtUrlIntegration + "/file-progress", HttpGet.METHOD_NAME, "api/applications/integrationFileProgress.json", fileProgressQueryParams()),
                RequestMock.build(builtUrlIntegration + "/crowdin-update", HttpPost.METHOD_NAME, "api/applications/updateIntegrationCrowdinFilesRequest.json", "api/applications/integrationJobId.json"),
                RequestMock.build(builtUrlIntegration + "/integration-update", HttpPost.METHOD_NAME, "api/applications/updateIntegrationFilesRequest.json", "api/applications/integrationJobId.json"),
                RequestMock.build(builtUrlIntegration + "/all-jobs", HttpGet.METHOD_NAME, "api/applications/integrationJobs.json", allJobsQueryParams()),
                RequestMock.build(builtUrlIntegration + "/jobs", HttpDelete.METHOD_NAME, null, jobQueryParams()),
                RequestMock.build(builtUrlIntegration + "/job-info", HttpGet.METHOD_NAME, "api/applications/integrationJobs.json", jobQueryParams()),
                RequestMock.build(builtUrlIntegration + "/settings", HttpGet.METHOD_NAME, "api/applications/integrationSettings.json", projectQueryParams()),
                RequestMock.build(builtUrlIntegration + "/settings", HttpPost.METHOD_NAME, "api/applications/updateIntegrationSettingsRequest.json", (String) null),
                RequestMock.build(builtUrlIntegration + "/sync-settings", HttpGet.METHOD_NAME, "api/applications/integrationSyncSettingsMap.json", syncSettingsQueryParams("crowdin")),
                RequestMock.build(builtUrlIntegration + "/sync-settings", HttpGet.METHOD_NAME, "api/applications/integrationSyncSettingsList.json", syncSettingsQueryParams("integration")),
                RequestMock.build(builtUrlIntegration + "/sync-settings", HttpPost.METHOD_NAME, "api/applications/updateIntegrationSyncSettingsRequest.json", (String) null),
                RequestMock.build(builtUrlIntegration + "/login-fields", HttpGet.METHOD_NAME, "api/applications/integrationLoginFields.json"),
                RequestMock.build(builtUrlIntegration + "/login", HttpPost.METHOD_NAME, "api/applications/integrationLoginRequest.json", (String) null)
        );
    }

    private final String builtUrlKv = this.url + "/applications/" + applicationIdentifier + "/storage/kv/records";
    private final String builtUrlIntegration = this.url + "/applications/" + applicationIdentifier + "/api";
    private final String kvKey = "user:1:settings.theme";
    private final String encodedKvKey = "user%3A1%3Asettings.theme";
    private final Long projectId = 12L;
    private final String jobId = "067da473-fc0b-43e3-b0a2-09d26af130c1";

    private static Map<String, Object> dataQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("filter", "abc");
        params.put("limit", 10);
        return params;
    }

    private static Map<String, Object> installationsQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("installedBy", 12L);
        params.put("orderBy", "createdAt%20desc");
        params.put("limit", 10);
        return params;
    }

    private static Map<String, Object> kvQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("prefix", "user:1:");
        params.put("orderBy", "key%20asc");
        params.put("limit", 25);
        params.put("offset", 0);
        return params;
    }

    private Map<String, Object> projectQueryParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        return params;
    }

    private Map<String, Object> fileProgressQueryParams() {
        Map<String, Object> params = projectQueryParams();
        params.put("fileId", 102L);
        return params;
    }

    private Map<String, Object> allJobsQueryParams() {
        Map<String, Object> params = projectQueryParams();
        params.put("limit", 10);
        params.put("offset", 5);
        return params;
    }

    private Map<String, Object> jobQueryParams() {
        Map<String, Object> params = projectQueryParams();
        params.put("jobId", jobId);
        return params;
    }

    private Map<String, Object> syncSettingsQueryParams(String provider) {
        Map<String, Object> params = projectQueryParams();
        params.put("provider", provider);
        return params;
    }

    @Test
    void getApplicationData() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().getApplicationData(applicationIdentifier, path);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void updateOrRestoreApplicationData() {
        Map<String, Object> applicationDataPayload = new HashMap<>();
        applicationDataPayload.put("updateKey", "value");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().updateOrRestoreApplicationData(applicationIdentifier, path, applicationDataPayload);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void addApplicationData() {
        Map<String, Object> addApplicationDataPayload = new HashMap<>();
        addApplicationDataPayload.put("Key1", "value");
        addApplicationDataPayload.put("Key2", "value");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().addApplicationData(applicationIdentifier, path, addApplicationDataPayload);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void deleteApplicationData() {
        this.getApplicationsApi().deleteApplicationData(applicationIdentifier, path);
    }

    @Test
    void editApplicationData() {
        Map<String, Object> request = new HashMap<>();
        request.put("editKey", "editedValue");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().editApplicationData(applicationIdentifier, path, request);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void getApplicationInstallation() {
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().getApplicationInstallation(applicationIdentifier);
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }

    @Test
    void listApplicationInstallations() {
        ResponseList<ApplicationInstallation> responseList = this.getApplicationsApi().listApplicationInstallations(null, null);
        assertNotNull(responseList);
        assertEquals(1, responseList.getData().size());
    }

    @Test
    void installApplication() {
        InstallApplicationRequest request = new InstallApplicationRequest();
        request.setUrl("https://localhost.dev/crowdin.json");
        User user = new User();
        user.setValue(UserPermissions.RESTRICTED);
        user.setIds(new int[]{1});
        Project project = new Project();
        project.setValue(ProjectPermissions.RESTRICTED);
        project.setIds(new int[]{1});
        Permissions permissions = new Permissions();
        permissions.setUser(user);
        permissions.setProject(project);
        request.setPermissions(permissions);
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().installApplication(request);
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }

    @Test
    void deleteApplicationInstallation() {
        this.getApplicationsApi().deleteApplicationInstallation(applicationIdentifier);
    }

    @Test
    void editApplicationInstallation() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/permissions");
        request.setValue("example-application");
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().editApplicationInstallation(applicationIdentifier, singletonList(request));
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }

    @Test
    void listApplicationConsents() {
        ResponseList<ApplicationConsent> responseList = this.getApplicationsApi().listApplicationConsents(null);
        assertNotNull(responseList);
        assertEquals(1, responseList.getData().size());
        assertEquals(responseList.getData().get(0).getData().getIdentifier(), "example-application");
        assertEquals(responseList.getData().get(0).getData().getStatus(), ConsentStatus.GRANTED);
    }

    @Test
    void addApplicationConsent() {
        AddApplicationConsentRequest request = new AddApplicationConsentRequest();
        request.setIdentifier("example-application");
        request.setInstalledBy(consentId);
        request.setStatus(ConsentStatus.GRANTED);
        request.setScopes(Arrays.asList("project", "tm"));
        ResponseObject<ApplicationConsent> response = this.getApplicationsApi().addApplicationConsent(request);
        assertNotNull(response);
        assertEquals(response.getData().getId(), consentId);
        assertEquals(response.getData().getStatus(), ConsentStatus.GRANTED);
    }

    @Test
    void editApplicationConsent() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/status");
        request.setValue("denied");
        ResponseObject<ApplicationConsent> response = this.getApplicationsApi().editApplicationConsent(consentId, singletonList(request));
        assertNotNull(response);
        assertEquals(response.getData().getId(), consentId);
    }

    @Test
    void deleteApplicationConsent() {
        this.getApplicationsApi().deleteApplicationConsent(consentId);
    }

    @Test
    void getApplicationDataWithQueryParams() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().getApplicationData(applicationIdentifier, path, dataQueryParams());
        assertEquals("value", response.getData().get("customKey1"));
    }

    @Test
    void deleteApplicationDataWithQueryParams() {
        Map<String, Object> params = dataQueryParams();
        params.put("ignored", null);
        this.getApplicationsApi().deleteApplicationData(applicationIdentifier, path, params);
    }

    @Test
    void getApplicationInstallation_newFields() {
        ApplicationInstallation installation = this.getApplicationsApi().getApplicationInstallation(applicationIdentifier).getData();
        assertEquals(Long.valueOf(12), installation.getInstalledBy().getId());
        assertEquals("john_smith", installation.getInstalledBy().getUsername());
        assertEquals("John Smith", installation.getInstalledBy().getFullName());
        assertEquals("", installation.getInstalledBy().getAvatarUrl());
        assertEquals("https://localhost.dev/resources/images/logo.png", installation.getLogoUrl());
        assertEquals(Long.valueOf(13), installation.getAgent().getId());
        assertEquals("My App", installation.getManifest().get("name"));
        assertNotNull(installation.getManifestUpdatedAt());
        assertTrue(installation.getIsManifestOutdated());
        assertTrue(installation.getStringBasedAvailable());
        assertEquals(BundleMode.EXTERNAL, installation.getBundle().getMode());
        assertEquals("http://localhost:8080/", installation.getBundle().getUrl());
        Module module = installation.getModules()[0];
        assertEquals(UserPermissions.RESTRICTED, module.getPermissions().getUser().getValue());
        assertArrayEquals(new int[]{1}, module.getPermissions().getUser().getIds());
        assertEquals(ProjectPermissions.RESTRICTED, installation.getPermissions().getProject().getValue());
    }

    @Test
    void listApplicationInstallationsWithParams() {
        ListApplicationInstallationsParams params = new ListApplicationInstallationsParams();
        params.setInstalledBy(12L);
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("createdAt");
        orderBy.setOrderBy(SortOrder.DESC);
        params.setOrderBy(singletonList(orderBy));
        params.setLimit(10);
        ResponseList<ApplicationInstallation> responseList = this.getApplicationsApi().listApplicationInstallations(params);
        assertEquals(1, responseList.getData().size());
        assertEquals("example-application", responseList.getData().get(0).getData().getIdentifier());
    }

    @Test
    void installApplicationWithModules() {
        InstallApplicationRequest request = new InstallApplicationRequest();
        request.setUrl("https://localhost.dev/crowdin.json");
        Project project = new Project();
        project.setValue(ProjectPermissions.OWN);
        Permissions permissions = new Permissions();
        permissions.setProject(project);
        request.setPermissions(permissions);
        User user = new User();
        user.setValue(UserPermissions.RESTRICTED);
        user.setIds(new int[]{1});
        ModulePermissions modulePermissions = new ModulePermissions();
        modulePermissions.setUser(user);
        InstallApplicationModule module = new InstallApplicationModule();
        module.setKey("some-module-key");
        module.setPermissions(modulePermissions);
        request.setModules(singletonList(module));
        request.setAssignAgent(true);
        request.setManifestHash("5536b952d54e0846b6fd69217f6eb7cfdcfba41774732926e1f0c1a158a6e405");
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().installApplication(request);
        assertEquals("example-application", response.getData().getIdentifier());
    }

    @Test
    void installApplicationFromManifest() {
        User user = new User();
        user.setValue(UserPermissions.ALL);
        ModulePermissions modulePermissions = new ModulePermissions();
        modulePermissions.setUser(user);
        ApplicationManifestModule module = new ApplicationManifestModule();
        module.setKey("my-module");
        module.setName("My Module");
        module.setEnvironments(Arrays.asList("crowdin", "crowdin-enterprise"));
        module.setPermissions(modulePermissions);
        Map<String, List<ApplicationManifestModule>> modules = new HashMap<>();
        modules.put("modal", singletonList(module));
        DefaultPermissions defaultPermissions = new DefaultPermissions();
        defaultPermissions.setUser(UserPermissions.MANAGERS);
        defaultPermissions.setProject(ProjectPermissions.RESTRICTED);
        ApplicationBundle bundle = new ApplicationBundle();
        bundle.setMode(BundleMode.INTERNAL);
        ApplicationManifest manifest = new ApplicationManifest();
        manifest.setName("My App");
        manifest.setDescription("Serverless app");
        manifest.setLogo("/logo.png");
        manifest.setScopes(singletonList("project"));
        manifest.setStringBasedAvailable(true);
        manifest.setModules(modules);
        manifest.setDefault_permissions(defaultPermissions);
        manifest.setBundle(bundle);
        InstallApplicationRequest request = new InstallApplicationRequest();
        request.setManifest(manifest);
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().installApplication(request);
        assertEquals("example-application", response.getData().getIdentifier());
    }

    @Test
    void uploadApplicationBundle() {
        UploadApplicationBundleRequest request = new UploadApplicationBundleRequest();
        request.setStorageId(12L);
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().uploadApplicationBundle(applicationIdentifier, request);
        assertEquals("example-application", response.getData().getIdentifier());
    }

    @Test
    void getApplicationInstallationUpdate() {
        ApplicationInstallationUpdate update = this.getApplicationsApi().getApplicationInstallationUpdate(applicationIdentifier).getData();
        assertEquals("5536b952d54e0846b6fd69217f6eb7cfdcfba41774732926e1f0c1a158a6e405", update.getManifestHash());
        assertEquals("My App", update.getLatestManifest().get("name"));
        assertEquals(singletonList("tm"), update.getAddedScopes());
        assertEquals(singletonList("project"), update.getRemovedScopes());
        assertEquals("new-module", update.getAddedModules().get(0).get("key"));
        assertTrue(update.getRemovedModules().isEmpty());
        assertEquals("changed-module", update.getChangedModules().get(0).get("key"));
        assertEquals("/installed", update.getChangedEvents().get("installed").getFrom());
        assertEquals("/installed-v2", update.getChangedEvents().get("installed").getTo());
        assertEquals("https://old.dev", update.getBaseUrlChanged().getFrom());
        assertEquals("https://new.dev", update.getBaseUrlChanged().getTo());
        assertNull(update.getAuthenticationTypeChanged());
        assertTrue(update.getHasChanges());
    }

    @Test
    void applyApplicationInstallationUpdate() {
        ApplyApplicationInstallationUpdateRequest request = new ApplyApplicationInstallationUpdateRequest();
        request.setManifestHash("5536b952d54e0846b6fd69217f6eb7cfdcfba41774732926e1f0c1a158a6e405");
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().applyApplicationInstallationUpdate(applicationIdentifier, request);
        assertEquals("example-application", response.getData().getIdentifier());
    }

    @Test
    void listApplicationKvRecords() {
        ListApplicationKvRecordsParams params = new ListApplicationKvRecordsParams();
        params.setPrefix("user:1:");
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("key");
        params.setOrderBy(singletonList(orderBy));
        params.setLimit(25);
        params.setOffset(0);
        ResponseList<ApplicationKvRecord> responseList = this.getApplicationsApi().listApplicationKvRecords(applicationIdentifier, params);
        assertEquals(2, responseList.getData().size());
        assertEquals(Integer.valueOf(25), responseList.getPagination().getLimit());
        ApplicationKvRecord second = responseList.getData().get(1).getData();
        assertEquals("counter", second.getKey());
        assertEquals(5, second.getValue());
        assertTrue(second.getSecret());
        assertNull(second.getExpiresAt());
    }

    @Test
    void addApplicationKvRecord() {
        Map<String, Object> value = new LinkedHashMap<>();
        value.put("theme", "dark");
        value.put("size", 12);
        AddApplicationKvRecordRequest request = new AddApplicationKvRecordRequest();
        request.setKey(kvKey);
        request.setValue(value);
        request.setSecret(false);
        request.setTtl(3600);
        ResponseObject<ApplicationKvRecord> response = this.getApplicationsApi().addApplicationKvRecord(applicationIdentifier, request);
        assertEquals(kvKey, response.getData().getKey());
    }

    @Test
    void getApplicationKvRecord() {
        ApplicationKvRecord record = this.getApplicationsApi().getApplicationKvRecord(applicationIdentifier, kvKey).getData();
        assertEquals(kvKey, record.getKey());
        assertTrue(record.getValue() instanceof Map);
        assertEquals("dark", ((Map<?, ?>) record.getValue()).get("theme"));
        assertFalse(record.getSecret());
        assertNotNull(record.getCreatedAt());
        assertNotNull(record.getUpdatedAt());
        assertNotNull(record.getExpiresAt());
    }

    @Test
    void editApplicationKvRecord() {
        PatchRequest valuePatch = new PatchRequest();
        valuePatch.setOp(PatchOperation.REPLACE);
        valuePatch.setPath("/value");
        valuePatch.setValue(Arrays.asList("a", "b"));
        ApplicationKvRecordPatchRequest ttlPatch = new ApplicationKvRecordPatchRequest();
        ttlPatch.setOp(PatchOperation.REPLACE);
        ttlPatch.setPath("/ttl");
        ttlPatch.setValue(null);
        ResponseObject<ApplicationKvRecord> response = this.getApplicationsApi().editApplicationKvRecord(applicationIdentifier, kvKey, Arrays.asList(valuePatch, ttlPatch));
        assertEquals(kvKey, response.getData().getKey());
    }

    @Test
    void deleteApplicationKvRecord() {
        this.getApplicationsApi().deleteApplicationKvRecord(applicationIdentifier, kvKey);
    }

    @Test
    void listIntegrationCrowdinFiles() {
        ResponseList<IntegrationFile> responseList = this.getApplicationsApi().listIntegrationCrowdinFiles(applicationIdentifier, projectId);
        assertEquals(2, responseList.getData().size());
        IntegrationFile file = responseList.getData().get(1).getData();
        assertEquals("2", file.getId());
        assertEquals("1", file.getParentId());
        assertEquals("Home Page", file.getName());
        assertEquals("json", file.getType());
    }

    @Test
    void listIntegrationFiles() {
        ResponseList<IntegrationFile> responseList = this.getApplicationsApi().listIntegrationFiles(applicationIdentifier, projectId);
        assertEquals(2, responseList.getData().size());
        IntegrationFile folder = responseList.getData().get(0).getData();
        assertEquals("0", folder.getParentIdSnakeCase());
        assertEquals("0", folder.getNodeType());
        IntegrationFile file = responseList.getData().get(1).getData();
        assertEquals("73291251883", file.getId());
        assertEquals("Landing pages", file.getParentId());
        assertEquals("1", file.getNodeType());
    }

    @Test
    void getIntegrationFileProgress() {
        IntegrationFileProgress progress = this.getApplicationsApi().getIntegrationFileProgress(applicationIdentifier, projectId, 102L).getData();
        assertEquals("af", progress.getLanguageId());
        assertEquals("fd0ea167420ef1687fd16635b9fb67a3", progress.getETag());
        assertEquals(Integer.valueOf(7249), progress.getWords().getTotal());
        assertEquals(Integer.valueOf(3651), progress.getWords().getTranslated());
        assertEquals(Integer.valueOf(3637), progress.getWords().getApproved());
        assertEquals(Integer.valueOf(2622), progress.getPhrases().getApproved());
        assertEquals(Integer.valueOf(86), progress.getTranslationProgress());
        assertEquals(Integer.valueOf(85), progress.getApprovalProgress());
    }

    @Test
    void updateIntegrationCrowdinFiles() {
        IntegrationFile folder = new IntegrationFile();
        folder.setId("1");
        folder.setName("Landing pages");
        folder.setParentIdSnakeCase("0");
        folder.setNodeType("0");
        IntegrationFile file = new IntegrationFile();
        file.setName("Intro");
        file.setId("73291251883");
        file.setParentId("Landing pages");
        file.setType("json");
        file.setNodeType("1");
        UpdateIntegrationCrowdinFilesRequest request = new UpdateIntegrationCrowdinFilesRequest();
        request.setProjectId(projectId);
        request.setFiles(Arrays.asList(folder, file));
        request.setUploadTranslations(true);
        ResponseObject<IntegrationJobId> response = this.getApplicationsApi().updateIntegrationCrowdinFiles(applicationIdentifier, request);
        assertEquals(jobId, response.getData().getJobId());
    }

    @Test
    void updateIntegrationFiles() {
        Map<String, List<String>> files = new LinkedHashMap<>();
        files.put("102", Arrays.asList("de", "fr"));
        files.put("999", singletonList("uk"));
        UpdateIntegrationFilesRequest request = new UpdateIntegrationFilesRequest();
        request.setProjectId(projectId);
        request.setFiles(files);
        ResponseObject<IntegrationJobId> response = this.getApplicationsApi().updateIntegrationFiles(applicationIdentifier, request);
        assertEquals(jobId, response.getData().getJobId());
    }

    @Test
    void listIntegrationJobs() {
        ResponseList<IntegrationJob> responseList = this.getApplicationsApi().listIntegrationJobs(applicationIdentifier, projectId, 10, 5);
        assertEquals(1, responseList.getData().size());
        IntegrationJob job = responseList.getData().get(0).getData();
        assertEquals(jobId, job.getId());
        assertEquals(Integer.valueOf(94), job.getProgress());
        assertEquals("inProgress", job.getStatus());
        assertEquals("Sync files to Crowdin", job.getTitle());
    }

    @Test
    void getIntegrationJobInfo() {
        ResponseList<IntegrationJob> responseList = this.getApplicationsApi().getIntegrationJobInfo(applicationIdentifier, projectId, jobId);
        assertEquals(jobId, responseList.getData().get(0).getData().getId());
    }

    @Test
    void cancelIntegrationJob() {
        this.getApplicationsApi().cancelIntegrationJob(applicationIdentifier, projectId, jobId);
    }

    @Test
    void getIntegrationSettings() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().getIntegrationSettings(applicationIdentifier, projectId);
        assertEquals("0", response.getData().get("schedule"));
        assertEquals("1", response.getData().get("condition"));
    }

    @Test
    void updateIntegrationSettings() {
        Map<String, Object> config = new LinkedHashMap<>();
        config.put("schedule", "0");
        config.put("condition", "0");
        UpdateIntegrationSettingsRequest request = new UpdateIntegrationSettingsRequest();
        request.setProjectId(projectId);
        request.setConfig(config);
        this.getApplicationsApi().updateIntegrationSettings(applicationIdentifier, request);
    }

    @Test
    void getIntegrationSyncSettings() {
        Object mapSettings = this.getApplicationsApi().getIntegrationSyncSettings(applicationIdentifier, projectId, IntegrationSyncProvider.CROWDIN).getData();
        assertTrue(mapSettings instanceof Map);
        assertEquals(Arrays.asList("uk", "de"), ((Map<?, ?>) mapSettings).get("102"));

        Object listSettings = this.getApplicationsApi().getIntegrationSyncSettings(applicationIdentifier, projectId, IntegrationSyncProvider.INTEGRATION).getData();
        assertTrue(listSettings instanceof List);
        assertEquals("Intro", ((Map<?, ?>) ((List<?>) listSettings).get(0)).get("name"));
        assertEquals(true, ((Map<?, ?>) ((List<?>) listSettings).get(0)).get("schedule"));
    }

    @Test
    void updateIntegrationSyncSettings() {
        Map<String, List<String>> files = new HashMap<>();
        files.put("102", Arrays.asList("uk", "de"));
        UpdateIntegrationSyncSettingsRequest request = new UpdateIntegrationSyncSettingsRequest();
        request.setProjectId(projectId);
        request.setProvider(IntegrationSyncProvider.CROWDIN);
        request.setFiles(files);
        this.getApplicationsApi().updateIntegrationSyncSettings(applicationIdentifier, request);
    }

    @Test
    void listIntegrationLoginFields() {
        ResponseList<IntegrationLoginField> responseList = this.getApplicationsApi().listIntegrationLoginFields(applicationIdentifier);
        assertEquals(2, responseList.getData().size());
        assertEquals("email", responseList.getData().get(0).getData().getKey());
        assertEquals("User email", responseList.getData().get(0).getData().getName());
    }

    @Test
    void loginToIntegration() {
        Map<String, Object> credentials = new LinkedHashMap<>();
        credentials.put("email", "user@crowdin.com");
        credentials.put("password", "password");
        IntegrationLoginRequest request = new IntegrationLoginRequest();
        request.setProjectId(projectId);
        request.setCredentials(credentials);
        this.getApplicationsApi().loginToIntegration(applicationIdentifier, request);
    }
}
