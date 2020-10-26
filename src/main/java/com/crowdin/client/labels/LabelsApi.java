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

    public ResponseList<Label> listLabels(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset)
        );
        LabelResponseList labelResponseList = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), LabelResponseList.class);
        return LabelResponseList.to(labelResponseList);
    }

    public ResponseObject<Label> addLabel(Long projectId, AddLabelRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels", this.url, projectId);
        LabelResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public ResponseObject<Label> getLabel(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        LabelResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public void deleteLabel(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    public ResponseObject<Label> editLabel(Long projectId, Long labelId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d", this.url, projectId, labelId);
        LabelResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), LabelResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public ResponseList<SourceString> assignLabelToStrings(Long projectId, Long labelId, LabelToStringsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId);
        SourceStringResponseList response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), SourceStringResponseList.class);
        return SourceStringResponseList.to(response);
    }

    public ResponseList<SourceString> unassignLabelToStrings(Long projectId, Long labelId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/labels/%d/strings", this.url, projectId, labelId);
        SourceStringResponseList response = this.httpClient.delete(builtUrl, new HttpRequestConfig(), SourceStringResponseList.class);
        return SourceStringResponseList.to(response);
    }


}
