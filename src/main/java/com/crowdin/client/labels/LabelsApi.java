package com.crowdin.client.labels;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.labels.model.AddLabelRequest;
import com.crowdin.client.labels.model.Label;
import com.crowdin.client.labels.model.LabelResponseList;
import com.crowdin.client.labels.model.LabelResponseObject;
import com.crowdin.client.labels.model.LabelToStringsRequest;
import com.crowdin.client.sourcestrings.model.SourceString;
import com.crowdin.client.sourcestrings.model.SourceStringResponseList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LabelsApi extends CrowdinApi {

    public LabelsApi(Credentials credentials) {
        super(credentials);
    }

    public LabelsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId Project Identifier
     * @param limit A maximum number of items to retrieve. Default: 25
     * @param offset A starting offset in the collection. Default: 0
     * @return list of labels
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Label> listLabels(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset)
        );
        LabelResponseList labelResponseList = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), LabelResponseList.class);
        return LabelResponseList.to(labelResponseList);
    }

    /**
     * @param projectId Project Identifier
     * @param request Request object
     * @return created label
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Label> addLabel(Long projectId, AddLabelRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels", this.url, projectId);
        LabelResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId Project Identifier
     * @param labelId Label Identifier
     * @return label
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Label> getLabel(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        LabelResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId Project Identifier
     * @param labelId Label Identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteLabel(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId Project Identifier
     * @param labelId Label Identifier
     * @param request Request object
     * @return edited label
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Label> editLabel(Long projectId, Long labelId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        LabelResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId Project Identifier
     * @param labelId Label Identifier
     * @param request Request object
     * @return Source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.strings.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.strings.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SourceString> assignLabelToStrings(Long projectId, Long labelId, LabelToStringsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId);
        SourceStringResponseList response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), SourceStringResponseList.class);
        return SourceStringResponseList.to(response);
    }

    /**
     * @param projectId Project Identifier
     * @param labelId Label Identifier
     * @return Source string
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.labels.strings.deleteMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.labels.strings.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SourceString> unassignLabelFromStrings(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId);
        SourceStringResponseList response = this.httpClient.delete(builtUrl, new HttpRequestConfig(), SourceStringResponseList.class);
        return SourceStringResponseList.to(response);
    }


}
