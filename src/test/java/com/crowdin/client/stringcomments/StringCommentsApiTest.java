package com.crowdin.client.stringcomments;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.stringcomments.model.AddStringCommentRequest;
import com.crowdin.client.stringcomments.model.IssueStatus;
import com.crowdin.client.stringcomments.model.StringComment;
import com.crowdin.client.stringcomments.model.Type;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StringCommentsApiTest extends TestClient {

    private final Long projectId = 8L;
    private final Long stringId = 64L;
    private final Long stringCommentId = 512L;

    private final String text = "some issue";
    private final String targetLanguageId = "en";
    private final Type type = Type.ISSUE;
    private final String issueType = "translation_mistake";
    private final IssueStatus issueStatus = IssueStatus.RESOLVED;


    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, projectId), HttpGet.METHOD_NAME,
                "api/stringcomments/listStringCommentsResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, projectId), HttpPost.METHOD_NAME,
                "api/stringcomments/addStringCommentRequest.json", "api/stringcomments/stringCommentResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId), HttpGet.METHOD_NAME,
                "api/stringcomments/stringCommentResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId), HttpDelete.METHOD_NAME),
            RequestMock.build(String.format("%s/projects/%d/comments/%d", this.url, projectId, stringCommentId), HttpPatch.METHOD_NAME,
                "api/stringcomments/editStringCommentRequest.json", "api/stringcomments/stringCommentResponse.json"),
            RequestMock.build(
                    String.format("%s/projects/%d/comments", this.url, projectId),
                    HttpPatch.METHOD_NAME,
                    "api/stringcomments/stringCommentBatchOperationsRequest.json",
                    "api/stringcomments/stringCommentBatchOperationsResponse.json"
            )
        );
    }

    @Test
    public void listStringCommentsTest() {
        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(projectId, null, null, null, null, null, null);
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(1, responseList.getData().size(), "Size of list should be 1");
    }

    @Test
    public void addStringCommentTest() {
        AddStringCommentRequest request = new AddStringCommentRequest() {{
            setText(text);
            setTargetLanguageId(targetLanguageId);
            setStringId(stringId);
            setType(type);
            setIssueType(issueType);
            setIssueStatus(issueStatus);
        }};
        ResponseObject<StringComment> response = this.getStringCommentsApi().addStringComment(projectId, request);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    public void getStringCommentTest() {
        ResponseObject<StringComment> response = this.getStringCommentsApi().getStringComment(projectId, stringCommentId);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    public void deleteStringCommentTest() {
        this.getStringCommentsApi().deleteStringComment(projectId, stringCommentId);
    }

    @Test
    public void editStringCommentTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/text");
            }});
        }};
        ResponseObject<StringComment> response = this.getStringCommentsApi().editStringComment(projectId, stringCommentId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

    }

    @Test
    public void stringCommentBatchOperationsTest(){
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/2814/text");
                setValue("some issue edited");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/2814/issueStatus");
                setValue(IssueStatus.RESOLVED);
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.ADD);
                setPath("/-");
                setValue(new AddStringCommentRequest() {{
                    setText("some issue");
                    setStringId(1L);
                    setType(Type.ISSUE);
                    setTargetLanguageId("en");
                    setIssueType("translation_mistake");
                }});
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REMOVE);
                setPath("/2815");
            }});
        }};

        ResponseList<StringComment> response = this.getStringCommentsApi().stringCommentBatchOperations(projectId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

        assertEquals(IssueStatus.UNRESOLVED, response.getData().get(0).getData().getIssueStatus());
    }
}
