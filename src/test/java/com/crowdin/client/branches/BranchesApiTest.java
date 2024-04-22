package com.crowdin.client.branches;

import com.crowdin.client.branches.model.*;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BranchesApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long id = 14L;
    private final Long sourceBranchId = 8L;
    private final String cloneId = "aaee1685-c92a-4da6-8a08-c28b4db5cd4a";
    private final String mergeId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String name = "cloned";
    private final String title = "Cloned Branch";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/clones", HttpPost.METHOD_NAME, "api/branches/cloneBranchRequest.json", "api/branches/branchCloneStatus.json"),
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/clones/" + cloneId, HttpGet.METHOD_NAME, "api/branches/branchCloneStatus.json"),
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/merges", HttpPost.METHOD_NAME, "api/branches/mergeBranchRequest.json", "api/branches/branchMergeStatus.json"),
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/merges/" + mergeId, HttpGet.METHOD_NAME, "api/branches/branchMergeStatus.json"),
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/merges/" + mergeId + "/summary", HttpGet.METHOD_NAME, "api/branches/branchMergeSummary.json")
        );
    }

    @Test
    public void cloneBranchTest() {
        CloneBranchRequest request = new CloneBranchRequest();
        request.setName(name);
        request.setTitle(title);

        ResponseObject<BranchCloneStatus> response = this.getBranchesApi().cloneBranch(projectId, id, request);
        assertEquals(response.getData().getIdentifier(), cloneId);
    }

    @Test
    public void checkCloneBranchStatusTest() {
        ResponseObject<BranchCloneStatus> response = this.getBranchesApi().checkCloneBranchStatus(projectId, id, cloneId);
        assertEquals(response.getData().getIdentifier(), cloneId);
    }

    @Test
    public void mergeBranchTest() {
        MergeBranchRequest request = new MergeBranchRequest();
        request.setDeleteAfterMerge(true);
        request.setSourceBranchId(sourceBranchId);
        request.setDryRun(false);

        ResponseObject<BranchMergeStatus> response = this.getBranchesApi().mergeBranch(projectId, id, request);
        assertEquals(response.getData().getIdentifier(), mergeId);
    }

    @Test
    public void checkMergeBranchStatusTest() {
        ResponseObject<BranchMergeStatus> response = this.getBranchesApi().checkMergeBranchStatus(projectId, id, mergeId);
        assertEquals(response.getData().getIdentifier(), mergeId);
    }

    @Test
    public void getMergeBranchSummaryTest() {
        ResponseObject<BranchMergeSummary> response = this.getBranchesApi().getMergeBranchSummary(projectId, id, mergeId);
        assertEquals(response.getData().getTargetBranchId(), id);
        assertEquals(response.getData().getSourceBranchId(), sourceBranchId);
    }
}
