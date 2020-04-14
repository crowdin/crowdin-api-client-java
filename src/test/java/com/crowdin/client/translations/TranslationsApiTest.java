package com.crowdin.client.translations;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.translations.model.ApplyPreTranslationRequest;
import com.crowdin.client.translations.model.AutoApproveOption;
import com.crowdin.client.translations.model.BuildProjectFileTranslationRequest;
import com.crowdin.client.translations.model.BuildProjectTranslationRequest;
import com.crowdin.client.translations.model.Method;
import com.crowdin.client.translations.model.PreTranslationStatus;
import com.crowdin.client.translations.model.ProjectBuild;
import com.crowdin.client.translations.model.UploadTranslationsRequest;
import com.crowdin.client.translations.model.UploadTranslationsResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationsApiTest extends TestClient {

    private final Long projectId = 12L;
    private final String language = "uk";
    private final String preTranslationId = "9e7de270-4f83-41cb-b606-2f90631f26e2";
    private final Long fileId = 2L;
    private final Long storageId = 14L;
    private final Long buildId = 2L;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations", HttpPost.METHOD_NAME, "api/translations/preTranslationRequest.json", "api/translations/preTranslationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId, HttpGet.METHOD_NAME, "api/translations/preTranslationStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/files/" + fileId, HttpPost.METHOD_NAME, "api/translations/buildFileRequest.json", "api/translations/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds", HttpGet.METHOD_NAME, "api/translations/listProjectBuilds.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds", HttpPost.METHOD_NAME, "api/translations/buildProjectRequest.json", "api/translations/projectBuildStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/" + language, HttpPost.METHOD_NAME, "api/translations/uploadTranslationRequest.json", "api/translations/uploadTranslationResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId + "/download", HttpGet.METHOD_NAME, "api/translations/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, HttpGet.METHOD_NAME, "api/translations/projectBuildStatus.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, HttpDelete.METHOD_NAME)
        );
    }

    @Test
    public void applyPreTranslationTest() {
        ApplyPreTranslationRequest request = new ApplyPreTranslationRequest();
        request.setLanguageIds(singletonList(language));
        request.setFileIds(singletonList(fileId));
        request.setAutoApproveOption(AutoApproveOption.NONE);
        request.setMethod(Method.MT);
        ResponseObject<PreTranslationStatus> preTranslationStatusResponseObject = this.getTranslationsApi().applyPreTranslation(projectId, request);
        assertEquals(preTranslationStatusResponseObject.getData().getIdentifier(), preTranslationId);
    }

    @Test
    public void preTranslationStatusTest() {
        ResponseObject<PreTranslationStatus> preTranslationStatusResponseObject = this.getTranslationsApi().preTranslationStatus(projectId, preTranslationId);
        assertEquals(preTranslationStatusResponseObject.getData().getIdentifier(), preTranslationId);
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
        BuildProjectTranslationRequest request = new BuildProjectTranslationRequest();
        request.setTargetLanguageIds(singletonList(language));
        ResponseObject<ProjectBuild> projectBuildResponseObject = this.getTranslationsApi().buildProjectTranslation(projectId, request);
        assertEquals(projectBuildResponseObject.getData().getId(), buildId);
    }

    @Test
    public void uploadTranslationsTest() {
        UploadTranslationsRequest request = new UploadTranslationsRequest();
        request.setStorageId(storageId);
        request.setFileId(fileId);
        ResponseObject<UploadTranslationsResponse> uploadTranslationsResponseResponseObject = this.getTranslationsApi().uploadTranslations(projectId, language, request);
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
}
