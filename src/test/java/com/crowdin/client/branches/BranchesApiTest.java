package com.crowdin.client.branches;

import com.crowdin.client.branches.model.BranchCloneStatus;
import com.crowdin.client.branches.model.CloneBranchRequest;
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
    private final String cloneId = "aaee1685-c92a-4da6-8a08-c28b4db5cd4a";
    private final String name = "cloned";
    private final String title = "Cloned Branch";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/clones", HttpPost.METHOD_NAME, "api/branches/cloneBranchRequest.json", "api/branches/branchCloneStatus.json"),
            RequestMock.build(this.url + "/projects/" + projectId + "/branches/" + id + "/clones/" + cloneId, HttpGet.METHOD_NAME, "api/branches/branchCloneStatus.json")
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
    public void checkCloneBranchStatus() {
        ResponseObject<BranchCloneStatus> response = this.getBranchesApi().checkCloneBranchStatus(projectId, id, cloneId);
        assertEquals(response.getData().getIdentifier(), cloneId);
    }
}
