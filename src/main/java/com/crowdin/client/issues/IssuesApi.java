package com.crowdin.client.issues;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.issues.model.Issue;
import com.crowdin.client.issues.model.IssueResponseList;
import com.crowdin.client.issues.model.IssueResponseObject;
import com.crowdin.client.issues.model.Status;
import com.crowdin.client.issues.model.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IssuesApi extends CrowdinApi {
    public IssuesApi(Credentials credentials) {
        super(credentials);
    }

    public IssuesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @param type      filter by type
     * @param status    filter by status
     * @return list of issues
     */
    public ResponseList<Issue> listReportedIssues(Long projectId, Integer limit, Integer offset, Type type, Status status) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "type", Optional.ofNullable(type),
                "status", Optional.ofNullable(status)
        );
        IssueResponseList issueResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/issues", new HttpRequestConfig(queryParams), IssueResponseList.class);
        return IssueResponseList.to(issueResponseList);
    }

    /**
     * @param projectId project identifier
     * @param issueId   issue identifier
     * @param request   request object
     * @return updated issue
     */
    public ResponseObject<Issue> editIssue(Long projectId, Long issueId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        IssueResponseObject issueResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/issues/" + issueId, request, new HttpRequestConfig(), IssueResponseObject.class);
        return ResponseObject.of(issueResponseObject.getData());
    }
}
