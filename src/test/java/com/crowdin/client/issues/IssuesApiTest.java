package com.crowdin.client.issues;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.issues.model.Issue;
import com.crowdin.client.issues.model.Status;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssuesApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long issueId = 2L;
    private final Status status = Status.UNRESOLVED;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/issues", HttpGet.METHOD_NAME, "api/issues/listIssues.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/issues/" + issueId, HttpPatch.METHOD_NAME, "api/issues/editIssue.json", "api/issues/issue.json")
        );
    }

    @Test
    public void listReportedIssuesTest() {
        ResponseList<Issue> issueResponseList = this.getIssuesApi().listReportedIssues(projectId, null, null, null, null);
        assertEquals(issueResponseList.getData().size(), 1);
        assertEquals(issueResponseList.getData().get(0).getData().getId(), issueId);
        assertEquals(issueResponseList.getData().get(0).getData().getStatus(), status);
    }

    @Test
    public void editIssueTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(Status.UNRESOLVED);
        request.setPath("/status");
        ResponseObject<Issue> issueResponseObject = this.getIssuesApi().editIssue(projectId, issueId, singletonList(request));
        assertEquals(issueResponseObject.getData().getId(), issueId);
        assertEquals(issueResponseObject.getData().getStatus(), status);
    }
}
