package com.crowdin.client.branches;

import com.crowdin.client.branches.model.*;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DeleteJobStatus;
import com.crowdin.client.core.model.DeleteJobStatusResponseObject;
import com.crowdin.client.core.model.ResponseObject;

import java.util.Collections;

public class BranchesApi extends CrowdinApi {

    public BranchesApi(Credentials credentials) {
        super(credentials);
    }

    public BranchesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/string-based/#operation/api.projects.branches.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteBranch(Long projectId, Long branchId) throws HttpException, HttpBadRequestException {
        this.deleteBranch(projectId, branchId, null);
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param isAsync whether to delete the branch asynchronously
     * @return delete job status when deleting asynchronously, or {@code null} for a synchronous deletion
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/string-based/#operation/api.projects.branches.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DeleteJobStatus> deleteBranch(Long projectId, Long branchId, Boolean isAsync) throws HttpException, HttpBadRequestException {
        String url = this.url + "/projects/" + projectId + "/branches/" + branchId;
        if (!Boolean.TRUE.equals(isAsync)) {
            this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
            return null;
        }

        DeleteJobStatusResponseObject response = this.httpClient.delete(
                url,
                new HttpRequestConfig(Collections.emptyMap(), Collections.singletonMap("Prefer", "respond-async")),
                DeleteJobStatusResponseObject.class
        );
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param jobIdentifier delete job identifier
     * @return delete job status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.jobs.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/string-based/#operation/api.projects.branches.jobs.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DeleteJobStatus> checkBranchDeletionStatus(Long projectId, Long branchId, String jobIdentifier) throws HttpException, HttpBadRequestException {
        DeleteJobStatusResponseObject response = this.httpClient.get(
                this.url + "/projects/" + projectId + "/branches/" + branchId + "/jobs/" + jobIdentifier,
                new HttpRequestConfig(),
                DeleteJobStatusResponseObject.class
        );
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param request request object
     * @return clone status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.clones.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BranchCloneStatus> cloneBranch(Long projectId, Long branchId, CloneBranchRequest request) throws HttpException, HttpBadRequestException {
        BranchCloneStatusResponseObject branchResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/branches/" + branchId + "/clones", request, new HttpRequestConfig(), BranchCloneStatusResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param cloneId clone identifier
     * @return clone status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.clones.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BranchCloneStatus> checkCloneBranchStatus(Long projectId, Long branchId, String cloneId) throws HttpException, HttpBadRequestException {
        BranchCloneStatusResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId + "/clones/" + cloneId, new HttpRequestConfig(), BranchCloneStatusResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param cloneId clone identifier
     * @return cloned branch
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.clones.branch.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ClonedBranch> getClonedBranch(Long projectId, Long branchId, String cloneId) throws HttpException, HttpBadRequestException {
        ClonedBranchResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId + "/clones/" + cloneId + "/branch", new HttpRequestConfig(), ClonedBranchResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param request request object
     * @return merge status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.merges.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BranchMergeStatus> mergeBranch(Long projectId, Long branchId, MergeBranchRequest request) throws HttpException, HttpBadRequestException {
        BranchMergeStatusResponseObject branchResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/branches/" + branchId + "/merges", request, new HttpRequestConfig(), BranchMergeStatusResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param mergeId merge identifier
     * @return merge status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.clones.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BranchMergeStatus> checkMergeBranchStatus(Long projectId, Long branchId, String mergeId) throws HttpException, HttpBadRequestException {
        BranchMergeStatusResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId + "/merges/" + mergeId, new HttpRequestConfig(), BranchMergeStatusResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param branchId branch identifier
     * @param mergeId merge identifier
     * @return merge summary
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/string-based/#operation/api.projects.branches.merges.summary.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BranchMergeSummary> getMergeBranchSummary(Long projectId, Long branchId, String mergeId) throws HttpException, HttpBadRequestException {
        BranchMergeSummaryResponseObject branchResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/branches/" + branchId + "/merges/" + mergeId + "/summary", new HttpRequestConfig(), BranchMergeSummaryResponseObject.class);
        return ResponseObject.of(branchResponseObject.getData());
    }
}
