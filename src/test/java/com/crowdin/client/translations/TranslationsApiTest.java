package com.crowdin.client.translations;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translations.model.ApplyPreTranslationRequest;
import com.crowdin.client.translations.model.ApplyPreTranslationStringsBasedRequest;
import com.crowdin.client.translations.model.AutoApproveOption;
import com.crowdin.client.translations.model.BuildProjectDirectoryTranslationRequest;
import com.crowdin.client.translations.model.BuildProjectFileTranslationRequest;
import com.crowdin.client.translations.model.CharTransformation;
import com.crowdin.client.translations.model.CrowdinTranslationCraeteProjectPseudoBuildForm;
import com.crowdin.client.translations.model.CrowdinTranslationCreateProjectBuildForm;
import com.crowdin.client.translations.model.CrowdinTranslationCreateProjectPseudoBuildForm;
import com.crowdin.client.translations.model.ExportProjectTranslationRequest;
import com.crowdin.client.translations.model.Method;
import com.crowdin.client.translations.model.PreTranslation;
import com.crowdin.client.translations.model.PreTranslationStatus;
import com.crowdin.client.translations.model.ProjectBuild;
import com.crowdin.client.translations.model.UploadTranslationsRequest;
import com.crowdin.client.translations.model.UploadTranslationsResponse;
import com.crowdin.client.translations.model.UploadTranslationsStringsRequest;
import com.crowdin.client.translations.model.UploadTranslationsStringsResponse;
import com.crowdin.client.translations.model.PreTranslationReportResponse;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TranslationsApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long parallelProjectId = 13L;
    private final String language = "uk";
    private final String preTranslationId = "9e7de270-4f83-41cb-b606-2f90631f26e2";
    private final long directoryId = 3L;
    private final Long fileId = 2L;
    private final Long branchId = 211L;
    private final Long storageId = 14L;
    private final Long buildId = 2L;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations", HttpPost.METHOD_NAME, "api/translations/preTranslationRequest.json", "api/translations/preTranslationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations", HttpPost.METHOD_NAME, "api/translations/preTranslationStringsBasedRequest.json", "api/translations/preTranslationStatusStringsBased.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId, HttpGet.METHOD_NAME, "api/translations/preTranslationStatus.json"),
                RequestMock.build(String.format("%s/projects/%d/translations/builds/directories/%d", this.url, projectId, directoryId), HttpPost.METHOD_NAME, "api/translations/buildProjectDirectoryRequest.json", "api/translations/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/files/" + fileId, HttpPost.METHOD_NAME, "api/translations/buildFileRequest.json", "api/translations/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds", HttpGet.METHOD_NAME, "api/translations/listProjectBuilds.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds", HttpPost.METHOD_NAME, "api/translations/buildProjectRequest.json", "api/translations/projectBuildStatus.json"),
                RequestMock.build(this.url + "/projects/" + parallelProjectId + "/translations/builds", HttpPost.METHOD_NAME, "api/translations/pseudoBuildProjectRequest.json", "api/translations/projectBuildStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + language, HttpPost.METHOD_NAME, "api/translations/uploadTranslationRequest.json", "api/translations/uploadTranslationResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + language, HttpPost.METHOD_NAME, "api/translations/uploadTranslationsStringsRequest.json", "api/translations/uploadTranslationsStringsResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId + "/download", HttpGet.METHOD_NAME, "api/translations/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, HttpGet.METHOD_NAME, "api/translations/projectBuildStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, HttpDelete.METHOD_NAME),
                RequestMock.build(String.format("%s/projects/%d/translations/exports", this.url, projectId), HttpPost.METHOD_NAME, "api/translations/exportProjectTranslationRequest.json", "api/translations/exportProjectTranslationResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations", HttpGet.METHOD_NAME, "api/translations/listPreTranslations.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId, HttpPatch.METHOD_NAME, "api/translations/editPreTranslationRequest.json", "api/translations/editPreTranslationResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId + "/report", HttpGet.METHOD_NAME, "api/translations/preTranslationReportResponse.json")
                );
    }

    @Test
    public void applyPreTranslationTest() {
        ApplyPreTranslationRequest request = new ApplyPreTranslationRequest();
        request.setLanguageIds(singletonList(language));
        request.setFileIds(singletonList(fileId));
        request.setAutoApproveOption(AutoApproveOption.NONE);
        request.setMethod(Method.MT);
        request.setLabelIds(singletonList(10000L));
        request.setExcludeLabelIds(singletonList(20000L));
        ResponseObject<PreTranslationStatus> preTranslationStatusResponseObject = this.getTranslationsApi().applyPreTranslation(projectId, request);
        assertEquals(preTranslationStatusResponseObject.getData().getIdentifier(), preTranslationId);

        assertEquals(10000L, preTranslationStatusResponseObject.getData().getAttributes().getLabelIds().get(0));
        assertEquals(20000L, preTranslationStatusResponseObject.getData().getAttributes().getExcludeLabelIds().get(0));
    }

    @Test
    public void applyPreTranslationStringsBasedTest() {
        ApplyPreTranslationStringsBasedRequest request = new ApplyPreTranslationStringsBasedRequest();
        request.setLanguageIds(singletonList(language));
        request.setBranchIds(singletonList(branchId));
        request.setAutoApproveOption(AutoApproveOption.NONE);
        request.setMethod(Method.MT);
        request.setLabelIds(singletonList(10000L));
        request.setExcludeLabelIds(singletonList(20000L));
        ResponseObject<PreTranslationStatus> preTranslationStatusResponseObject = this.getTranslationsApi().applyPreTranslationStringsBased(projectId, request);
        assertEquals(preTranslationStatusResponseObject.getData().getIdentifier(), preTranslationId);

        assertEquals(10000L, preTranslationStatusResponseObject.getData().getAttributes().getLabelIds().get(0));
        assertEquals(20000L, preTranslationStatusResponseObject.getData().getAttributes().getExcludeLabelIds().get(0));
    }

    @Test
    public void preTranslationStatusTest() {
        ResponseObject<PreTranslationStatus> preTranslationStatusResponseObject = this.getTranslationsApi().preTranslationStatus(projectId, preTranslationId);
        assertEquals(preTranslationStatusResponseObject.getData().getIdentifier(), preTranslationId);

        assertEquals(10000L, preTranslationStatusResponseObject.getData().getAttributes().getLabelIds().get(0));
        assertEquals(20000L, preTranslationStatusResponseObject.getData().getAttributes().getExcludeLabelIds().get(0));
    }

    @Test
    public void buildProjectDirectoryTranslationTest() {
        BuildProjectDirectoryTranslationRequest request = new BuildProjectDirectoryTranslationRequest();
        request.setTargetLanguageIds(singletonList("uk"));
        request.setSkipUntranslatedStrings(false);
        request.setSkipUntranslatedFiles(false);
        request.setExportApprovedOnly(false);
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTranslationsApi().buildProjectDirectoryTranslation(projectId, directoryId, request);
        assertNotNull(downloadLinkResponseObject);
        assertNotNull(downloadLinkResponseObject.getData());
        assertEquals(link, downloadLinkResponseObject.getData().getUrl());
    }

    @Test
    public void buildProjectFileTranslationTest() {
        BuildProjectFileTranslationRequest request = new BuildProjectFileTranslationRequest();
        request.setTargetLanguageId(language);
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTranslationsApi().buildProjectFileTranslation(projectId, fileId, null, request);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void listProjectBuildsTest() {
        ResponseList<ProjectBuild> projectBuildResponseList = this.getTranslationsApi().listProjectBuilds(projectId, null, null, null);
        assertEquals(projectBuildResponseList.getData().size(), 1);
        assertEquals(projectBuildResponseList.getData().get(0).getData().getId(), buildId);
    }

    @Test
    public void buildProjectTranslationTest() {
        CrowdinTranslationCreateProjectBuildForm request = new CrowdinTranslationCreateProjectBuildForm();
        request.setTargetLanguageIds(singletonList(language));
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().buildProjectTranslation(projectId, request);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void pseudoBuildProjectTranslationTest() {
        CrowdinTranslationCreateProjectPseudoBuildForm request = new CrowdinTranslationCreateProjectPseudoBuildForm();
        request.setBranchId(1L);
        request.setPseudo(true);
        request.setPrefix("pre");
        request.setSuffix("ion");
        request.setLengthTransformation(0);
        request.setCharTransformation(CharTransformation.ASIAN);
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().buildProjectTranslation(parallelProjectId, request);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void pseudoBuildProjectTranslationCharTransformTest() {
        CrowdinTranslationCreateProjectPseudoBuildForm request = new CrowdinTranslationCreateProjectPseudoBuildForm();
        request.setBranchId(1L);
        request.setPseudo(true);
        request.setPrefix("pre");
        request.setSuffix("ion");
        request.setLengthTransformation(0);
        request.setCharTransformation(CharTransformation.from("asian"));
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().buildProjectTranslation(parallelProjectId, request);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void pseudoBuildProjectTranslationDeprecatedTest() {
        CrowdinTranslationCraeteProjectPseudoBuildForm request = new CrowdinTranslationCraeteProjectPseudoBuildForm();
        request.setBranchId(1L);
        request.setPseudo(true);
        request.setPrefix("pre");
        request.setSuffix("ion");
        request.setLengthTransformation(0);
        request.setCharTransformation(CharTransformation.ASIAN);
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().buildProjectTranslation(parallelProjectId, request);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void uploadTranslationsTest() {
        UploadTranslationsRequest request = new UploadTranslationsRequest();
        request.setStorageId(storageId);
        request.setFileId(fileId);
        request.setTranslateHidden(true);
        ResponseObject<UploadTranslationsResponse> uploadTranslationsResponseResponseObject = this.getTranslationsApi().uploadTranslations(projectId, language, request);
        assertEquals(uploadTranslationsResponseResponseObject.getData().getStorageId(), storageId);
    }

    @Test
    public void uploadTranslationStringsBasedTest() {
        UploadTranslationsStringsRequest request = new UploadTranslationsStringsRequest();
        request.setStorageId(storageId);
        request.setBranchId(branchId);
        request.setTranslateHidden(true);
        ResponseObject<UploadTranslationsStringsResponse> uploadTranslationsResponseResponseObject = this.getTranslationsApi().uploadTranslationStringsBased(projectId, language, request);
        assertEquals(uploadTranslationsResponseResponseObject.getData().getStorageId(), storageId);
    }

    @Test
    public void downloadProjectTranslationTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTranslationsApi().downloadProjectTranslations(projectId, buildId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void checkProjectBuildStatusTest() {
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().checkBuildStatus(projectId, buildId);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void cancelBuildTest() {
        this.getTranslationsApi().cancelBuild(projectId, buildId);
    }

    @Test
    public void exportProjectTranslationTest() {
        ExportProjectTranslationRequest request = new ExportProjectTranslationRequest();
        request.setTargetLanguageId("uk");
        request.setFormat("xliff");
        request.setLabelIds(singletonList(1L));
        request.setBranchIds(singletonList(1L));
        request.setDirectoryIds(singletonList(1L));
        request.setFileIds(singletonList(1L));
        request.setSkipUntranslatedStrings(false);
        request.setSkipUntranslatedFiles(false);
        request.setExportApprovedOnly(false);
        ResponseObject<DownloadLink> response = this.getTranslationsApi().exportProjectTranslation(projectId, request);
        assertEquals(link, response.getData().getUrl());
    }

    @Test
    public void listPreTranslationsTest() {
        ResponseList<PreTranslation> preTranslationsResponseList = this.getTranslationsApi().listPreTranslations(projectId, null, null);
        assertEquals(preTranslationsResponseList.getData().size(), 1);
        PreTranslation preTranslation = preTranslationsResponseList.getData().get(0).getData();
        assertEquals(preTranslation.getIdentifier(), preTranslationId);

        assertEquals(language, preTranslation.getAttributes().getLanguageIds().get(0));
        assertEquals(fileId, preTranslation.getAttributes().getFileIds().get(0));
    }

    @Test
    public void editPreTranslationTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/status");
        request.setValue("cancelled");

        ResponseObject<PreTranslation> preTranslationResponseObject = this.getTranslationsApi().editPreTranslation(projectId, preTranslationId, Arrays.asList(request));
        assertEquals(preTranslationResponseObject.getData().getIdentifier(), preTranslationId);

        assertEquals(language, preTranslationResponseObject.getData().getAttributes().getLanguageIds().get(0));
        assertEquals(fileId, preTranslationResponseObject.getData().getAttributes().getFileIds().get(0));
    }
    
    @Test
    public void getPreTranslationReportTest() {
    	 ResponseObject<PreTranslationReportResponse> response = this.getTranslationsApi().getPreTranslationReport(projectId, preTranslationId);    	 
    	 PreTranslationReportResponse report = response.getData();
         assertNotNull(report);
         assertEquals(Method.AI, report.getPreTranslateType());
         PreTranslationReportResponse.TargetLanguage lang = report.getLanguages().get(0);
         assertEquals(language, lang.getId());
         PreTranslationReportResponse.File file = lang.getFiles().get(0);
         assertEquals(fileId, file.getId());
    }
}
