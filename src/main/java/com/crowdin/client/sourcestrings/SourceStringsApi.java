package com.crowdin.client.sourcestrings;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.sourcestrings.model.AddSourceStringRequest;
import com.crowdin.client.sourcestrings.model.AddSourceStringStringsBasedRequest;
import com.crowdin.client.sourcestrings.model.SourceString;
import com.crowdin.client.sourcestrings.model.SourceStringResponseList;
import com.crowdin.client.sourcestrings.model.SourceStringResponseObject;
import com.crowdin.client.sourcestrings.model.UploadStringsProgress;
import com.crowdin.client.sourcestrings.model.UploadStringsProgressResponseObject;
import com.crowdin.client.sourcestrings.model.UploadStringsRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SourceStringsApi extends CrowdinApi {
    public SourceStringsApi(Credentials credentials) {
        super(credentials);
    }

    public SourceStringsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param uploadId upload identifier
     */
    public ResponseObject<UploadStringsProgress> uploadStringsStatus(Long projectId, String uploadId) throws HttpException, HttpBadRequestException {
        UploadStringsProgressResponseObject stringsProgressResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/strings/uploads/" + uploadId, new HttpRequestConfig(), UploadStringsProgressResponseObject.class);
        return ResponseObject.of(stringsProgressResponseObject.getData());
    }

    public ResponseObject<UploadStringsProgress> uploadStrings(Long projectId, UploadStringsRequest request) throws HttpException, HttpBadRequestException {
        UploadStringsProgressResponseObject stringsProgressResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/strings/uploads", request, new HttpRequestConfig(), UploadStringsProgressResponseObject.class);
        return ResponseObject.of(stringsProgressResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param fileId file identifier
     * @param denormalizePlaceholders [0, 1]. Enable denormalize placeholders
     * @param branchId project identifier
     * @param labelIds filter strings by labels
     * @param croql filter strings by croql
     * @param filter Filter strings
     * @param scope ["identifier", "text", "context"]. Specify field to be the target of filtering. It can be one scope or a list of comma-separated scopes
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of source strings
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SourceString> listSourceStrings(Long projectId, Long fileId, Integer denormalizePlaceholders, Long branchId, String labelIds, String croql, String filter, String scope, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "fileId", Optional.ofNullable(fileId),
                "denormalizePlaceholders", Optional.ofNullable(denormalizePlaceholders),
                "branchId", Optional.ofNullable(branchId),
                "labelIds", Optional.ofNullable(labelIds),
                "croql", Optional.ofNullable(croql),
                "filter", Optional.ofNullable(filter),
                "scope", Optional.ofNullable(scope),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        SourceStringResponseList sourceStringResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/strings", new HttpRequestConfig(queryParams), SourceStringResponseList.class);
        return SourceStringResponseList.to(sourceStringResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<SourceString> addSourceString(Long projectId, AddSourceStringRequest request) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/strings", request, new HttpRequestConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    public ResponseObject<SourceString> addSourceStringStringsBased(Long projectId, AddSourceStringStringsBasedRequest request) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/strings", request, new HttpRequestConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @return source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<SourceString> getSourceString(Long projectId, Long stringId) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/strings/" + stringId, new HttpRequestConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteSourceString(Long projectId, Long stringId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/strings/" + stringId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param stringId string identifier
     * @param request request object
     * @return updated source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<SourceString> editSourceString(Long projectId, Long stringId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/strings/" + stringId, request, new HttpRequestConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return updated source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.strings.batchPatch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.strings.batchPatch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SourceString> stringBatchOperations(Long projectId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String url = this.url + "/projects/" + projectId + "/strings";
        SourceStringResponseList sourceStringResponseList = this.httpClient.patch(url, request, new HttpRequestConfig(), SourceStringResponseList.class);
        return SourceStringResponseList.to(sourceStringResponseList);
    }
}
