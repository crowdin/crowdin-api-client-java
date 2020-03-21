package com.crowdin.client.sourcestrings;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.sourcestrings.model.AddSourceStringRequest;
import com.crowdin.client.sourcestrings.model.SourceString;
import com.crowdin.client.sourcestrings.model.SourceStringResponseList;
import com.crowdin.client.sourcestrings.model.SourceStringResponseObject;

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
     * @param fileId    file identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of source strings
     */
    public ResponseList<SourceString> listSourceStrings(Long projectId, Long fileId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "fileId", Optional.ofNullable(fileId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        SourceStringResponseList sourceStringResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/strings", new HttpConfig(queryParams), SourceStringResponseList.class);
        return SourceStringResponseList.to(sourceStringResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request   request object
     * @return newly created source string
     */
    public ResponseObject<SourceString> addSourceString(Long projectId, AddSourceStringRequest request) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/strings", request, new HttpConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId  string identifier
     * @return source string
     */
    public ResponseObject<SourceString> getSourceString(Long projectId, Long stringId) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/strings/" + stringId, new HttpConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringId  string identifier
     */
    public void deleteSourceString(Long projectId, Long stringId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/strings/" + stringId, new HttpConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param stringId  string identifier
     * @param request   request object
     * @return updated source string
     */
    public ResponseObject<SourceString> editSourceString(Long projectId, Long stringId, List<PatchOperation> request) throws HttpException, HttpBadRequestException {
        SourceStringResponseObject sourceStringResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/strings/" + stringId, request, new HttpConfig(), SourceStringResponseObject.class);
        return ResponseObject.of(sourceStringResponseObject.getData());
    }
}
