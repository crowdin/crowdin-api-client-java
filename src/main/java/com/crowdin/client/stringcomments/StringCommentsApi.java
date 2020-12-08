package com.crowdin.client.stringcomments;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.stringcomments.model.AddStringCommentRequest;
import com.crowdin.client.stringcomments.model.IssueStatus;
import com.crowdin.client.stringcomments.model.StringComment;
import com.crowdin.client.stringcomments.model.StringCommentResponseList;
import com.crowdin.client.stringcomments.model.StringCommentResponseObject;
import com.crowdin.client.stringcomments.model.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StringCommentsApi extends CrowdinApi {

    public StringCommentsApi(Credentials credentials) {
        super(credentials);
    }

    public StringCommentsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    public ResponseList<StringComment> listStringComments(Long projectId, Long stringId, Integer limit, Integer offset, Type type, String targetLanguageId, String issueType, IssueStatus issueStatus) {
        String builtUrl = String.format("%s/projects/%d/strings/%d/comments", this.url, projectId, stringId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset),
            "type", Optional.ofNullable(type),
            "targetLanguageId", Optional.ofNullable(targetLanguageId),
            "issueType", Optional.ofNullable(issueType),
            "issueStatus", Optional.ofNullable(issueStatus)
        );
        StringCommentResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), StringCommentResponseList.class);
        return StringCommentResponseList.to(response);
    }

    public ResponseObject<StringComment> addStringComment(Long projectId, Long stringId, AddStringCommentRequest request) {
        String builtUrl = String.format("%s/projects/%d/strings/%d/comments", this.url, projectId, stringId);
        StringCommentResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public ResponseObject<StringComment> getStringComment(Long projectId, Long stringId, Long stringCommentId) {
        String builtUrl = String.format("%s/projects/%d/strings/%d/comments/%d", this.url, projectId, stringId, stringCommentId);
        StringCommentResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    public void deleteStringComment(Long projectId, Long stringId, Long stringCommentId) {
        String builtUrl = String.format("%s/projects/%d/strings/%d/comments/%d", this.url, projectId, stringId, stringCommentId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    public ResponseObject<StringComment> editStringComment(Long projectId, Long stringId, Long stringCommentId, List<PatchRequest> request) {
        String builtUrl = String.format("%s/projects/%d/strings/%d/comments/%d", this.url, projectId, stringId, stringCommentId);
        StringCommentResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }
}
