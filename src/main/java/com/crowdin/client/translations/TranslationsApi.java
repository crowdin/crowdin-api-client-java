package com.crowdin.client.translations;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.translations.model.ApplyPreTranslationRequest;
import com.crowdin.client.translations.model.BuildProjectDirectoryTranslationRequest;
import com.crowdin.client.translations.model.BuildProjectFileTranslationRequest;
import com.crowdin.client.translations.model.BuildProjectTranslationRequest;
import com.crowdin.client.translations.model.ExportProjectTranslationRequest;
import com.crowdin.client.translations.model.PreTranslationStatus;
import com.crowdin.client.translations.model.PreTranslationStatusResponseObject;
import com.crowdin.client.translations.model.ProjectBuild;
import com.crowdin.client.translations.model.ProjectBuildResponseList;
import com.crowdin.client.translations.model.ProjectBuildResponseObject;
import com.crowdin.client.translations.model.UploadTranslationsRequest;
import com.crowdin.client.translations.model.UploadTranslationsResponse;
import com.crowdin.client.translations.model.UploadTranslationsResponseObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TranslationsApi extends CrowdinApi {
    public TranslationsApi(Credentials credentials) {
        super(credentials);
    }

    public TranslationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param request   request body
     * @return pre-translation status
     */
    public ResponseObject<PreTranslationStatus> applyPreTranslation(Long projectId, ApplyPreTranslationRequest request) throws HttpException, HttpBadRequestException {
        PreTranslationStatusResponseObject preTranslationStatusResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/pre-translations", request, new HttpRequestConfig(), PreTranslationStatusResponseObject.class);
        return ResponseObject.of(preTranslationStatusResponseObject.getData());
    }

    /**
     * @param projectId        project identifier
     * @param preTranslationId pre-translation identifier
     * @return pre-translation status
     */
    public ResponseObject<PreTranslationStatus> preTranslationStatus(Long projectId, String preTranslationId) throws HttpException, HttpBadRequestException {
        PreTranslationStatusResponseObject preTranslationStatusResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/pre-translations/" + preTranslationId, new HttpRequestConfig(), PreTranslationStatusResponseObject.class);
        return ResponseObject.of(preTranslationStatusResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param directoryId directory identifier
     * @param request request body
     * @return download link
     */
    public ResponseObject<DownloadLink> buildProjectDirectoryTranslation(Long projectId, Long directoryId, BuildProjectDirectoryTranslationRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/translations/builds/directories/%d", this.url, projectId, directoryId);
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId    file identifier
     * @param etag      Etag identifier
     * @param request   request body
     * @return download link
     */
    public ResponseObject<DownloadLink> buildProjectFileTranslation(Long projectId, Long fileId, String etag, BuildProjectFileTranslationRequest request) throws HttpException, HttpBadRequestException {
        Map<String, String> headers = new HashMap<>();
        if (etag != null) {
            headers.put("If-None-Match", etag);
        }
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.post(
                this.url + "/projects/" + projectId + "/translations/builds/files/" + fileId,
                request,
                new HttpRequestConfig(Collections.emptyMap(), headers),
                DownloadLinkResponseObject.class
        );
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId  filter by branchId
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of project builds
     */
    public ResponseList<ProjectBuild> listProjectBuilds(Long projectId, Long branchId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "branchId", Optional.ofNullable(branchId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ProjectBuildResponseList projectBuildResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/translations/builds", new HttpRequestConfig(queryParams), ProjectBuildResponseList.class);
        return ProjectBuildResponseList.to(projectBuildResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request body
     * @return project build status
     */
    public ResponseObject<ProjectBuild> buildProjectTranslation(Long projectId, BuildProjectTranslationRequest request) throws HttpException, HttpBadRequestException {
        ProjectBuildResponseObject projectBuildResponseObject = this.httpClient.post(
                this.url + "/projects/" + projectId + "/translations/builds",
                request,
                new HttpRequestConfig(),
                ProjectBuildResponseObject.class
        );
        return ResponseObject.of(projectBuildResponseObject.getData());
    }

    /**
     * @param projectId  project identifier
     * @param languageId language identifier
     * @param request    request body
     * @return upload translations response
     */
    public ResponseObject<UploadTranslationsResponse> uploadTranslations(Long projectId, String languageId, UploadTranslationsRequest request) throws HttpException, HttpBadRequestException {
        UploadTranslationsResponseObject projectBuildResponseObject = this.httpClient.post(
                this.url + "/projects/" + projectId + "/translations/" + languageId,
                request,
                new HttpRequestConfig(),
                UploadTranslationsResponseObject.class
        );
        return ResponseObject.of(projectBuildResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param buildId   build identifier
     * @return download link
     */
    public ResponseObject<DownloadLink> downloadProjectTranslations(Long projectId, Long buildId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/translations/builds/" + buildId + "/download", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param buildId   build identifier
     * @return project build status
     */
    public ResponseObject<ProjectBuild> checkBuildStatus(Long projectId, Long buildId) throws HttpException, HttpBadRequestException {
        ProjectBuildResponseObject projectBuildResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, new HttpRequestConfig(), ProjectBuildResponseObject.class);
        return ResponseObject.of(projectBuildResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param buildId   build identifier
     */
    public void cancelBuild(Long projectId, Long buildId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/translations/builds/" + buildId, new HttpRequestConfig(), Void.class);
    }

    /**
     * Export Project Translation
     *
     * @param projectId project identifier
     * @param request request body
     * @return download link
     */
    public ResponseObject<DownloadLink> exportProjectTranslation(Long projectId, ExportProjectTranslationRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/translations/exports", this.url, projectId);
        DownloadLinkResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public ResponseObject<DownloadLink> exportProjectTranslationEnterprise(Long projectId, ExportProjectTranslationRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/translations/exports/enterprise", this.url, projectId);
        DownloadLinkResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(response.getData());
    }
}
