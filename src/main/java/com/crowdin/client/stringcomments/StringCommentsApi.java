package com.crowdin.client.stringcomments;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
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

    /**
     * @param projectId project identifier
     * @param stringId string Identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param type defines string comment type
     * @param issueType defines issue type
     * @param issueStatus defines issue resolution status
     * @return list of string comments
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StringComment> listStringComments(Long projectId, Long stringId, Integer limit, Integer offset, Type type, String issueType, IssueStatus issueStatus) {
        String builtUrl = String.format("%s/projects/%d/comments", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset),
            "type", Optional.ofNullable(type),
            "stringId", Optional.ofNullable(stringId),
            "issueType", Optional.ofNullable(issueType),
            "issueStatus", Optional.ofNullable(issueStatus)
        );
        StringCommentResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), StringCommentResponseList.class);
        return StringCommentResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param stringId string Identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param type defines string comment type
     * @param issueType defines issue type
     * @param issueStatus defines issue resolution status
     * @param orderBy list of OrderByField
     * @return list of string comments
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StringComment> listStringComments(Long projectId, Long stringId, Integer limit, Integer offset, Type type, String issueType, IssueStatus issueStatus, List<OrderByField> orderBy) {
        String builtUrl = String.format("%s/projects/%d/comments", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "type", Optional.ofNullable(type),
                "stringId", Optional.ofNullable(stringId),
                "issueType", Optional.ofNullable(issueType),
                "issueStatus", Optional.ofNullable(issueStatus),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(orderBy))
        );
        StringCommentResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), StringCommentResponseList.class);
        return StringCommentResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created bundle string comment
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringComment> addStringComment(Long projectId, AddStringCommentRequest request) {
        String builtUrl = String.format("%s/projects/%d/comments", this.url, projectId);
        StringCommentResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringCommentId string comment identifier
     * @return string comment object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringComment> getStringComment(Long projectId, Long stringCommentId) {
        String builtUrl = String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId);
        StringCommentResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param stringCommentId string comment identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteStringComment(Long projectId, Long stringCommentId) {
        String builtUrl = String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param stringCommentId string comment identifier
     * @param request request object
     * @return updated string comment object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.comments.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.comments.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StringComment> editStringComment(Long projectId, Long stringCommentId, List<PatchRequest> request) {
        String builtUrl = String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId);
        StringCommentResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), StringCommentResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return list of updated string comment objects
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#operation/api.projects.comments.batchPatch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#operation/api.projects.comments.batchPatch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StringComment> stringCommentBatchOperations(Long projectId, List<PatchRequest> request) {
        String builtUrl = String.format("%s/projects/%d/comments", this.url, projectId);
        StringCommentResponseList response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), StringCommentResponseList.class);
        return StringCommentResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param commentId comment identifier
     * @param attachmentId attachment identifier
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#operation/api.projects.comments.attachments.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#operation/api.projects.comments.attachments.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAttachmentFromStringComment(Long projectId, Long commentId, Long attachmentId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/comments/%d/attachments/%d", this.url, projectId, commentId, attachmentId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }
}
