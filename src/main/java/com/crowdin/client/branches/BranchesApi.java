package com.crowdin.client.branches;

import com.crowdin.client.branches.model.BranchCloneStatus;
import com.crowdin.client.branches.model.BranchCloneStatusResponseObject;
import com.crowdin.client.branches.model.CloneBranchRequest;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseObject;

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
}
