package com.crowdin.client.stringcomments;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.stringcomments.model.AddStringCommentRequest;
import com.crowdin.client.stringcomments.model.Attachment;
import com.crowdin.client.stringcomments.model.IssueStatus;
import com.crowdin.client.stringcomments.model.StringComment;
import com.crowdin.client.stringcomments.model.Type;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCommentsApiTest extends TestClient {

    private final Long projectId = 8L;
    private final Long project2Id = 9L;
    private final Long project3Id = 10L;
    private final Long project4Id = 11L;
    private final Long fileId = 22L;
    private final Long stringId = 1L;
    private final Long stringCommentId = 512L;
    private final Long attachmentId = 123L;
    private final Long attachmentId2 = 10L;

    private final String text = "some issue with translation";
    private final String targetLanguageId = "en";
    private final Type type = Type.ISSUE;
    private final String issueType = "translation_mistake";
    private final IssueStatus issueStatus = IssueStatus.RESOLVED;


    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, projectId), HttpGet.METHOD_NAME,
                "api/stringcomments/listStringCommentsResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, project2Id), HttpGet.METHOD_NAME,
                "api/stringcomments/listStringCommentsResponseOrderByIdAsc.json", new HashMap<String, String>() {{
                        put("orderBy", "id%20asc");
                    }}),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, project3Id), HttpGet.METHOD_NAME,
                "api/stringcomments/listStringCommentsResponseOrderByIdDesc.json", new HashMap<String, String>(){{
                        put("orderBy", "id%20desc");
                    }}),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, projectId), HttpPost.METHOD_NAME,
                "api/stringcomments/addStringCommentRequest.json", "api/stringcomments/stringCommentResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, projectId), HttpPost.METHOD_NAME,
                "api/stringcomments/addFileCommentRequest.json", "api/stringcomments/fileCommentResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/comments", this.url, project4Id), HttpGet.METHOD_NAME,
                "api/stringcomments/listFileCommentsResponse.json", new HashMap<String, Object>() {{
                        put("fileId", fileId);
                        put("type", Type.COMMENT);
                    }}),
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
            ),
            RequestMock.build(String.format("%s/projects/%d/comments/%d/attachments/%d", this.url, projectId, stringCommentId, attachmentId), HttpDelete.METHOD_NAME)
        );
    }

    @Test
    public void listStringCommentsTest() {
        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(projectId, null, null, null, null, null, null);
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(1, responseList.getData().size(), "Size of list should be 1");
        assertEquals(attachmentId2, responseList.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void listStringCommentsTest_orderByNull() {
        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(
                projectId,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(1, responseList.getData().size(), "Size of list should be 1");
        assertEquals(attachmentId2, responseList.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void listStringCommentsTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(
                project2Id,
                null,
                null,
                null,
                null,
                null,
                null,
                Collections.singletonList(orderById)
        );
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(2, responseList.getData().size(), "Size of list should be 1");

        assertEquals(2, responseList.getData().get(0).getData().getId(), "Id of list should be 2");
        assertEquals(3, responseList.getData().get(1).getData().getId(), "Id of list should be 3");
        assertEquals(attachmentId2, responseList.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void listStringCommentsTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(
                project2Id,
                null,
                null,
                null,
                null,
                null,
                null,
                Collections.singletonList(orderById)
        );
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(2, responseList.getData().size(), "Size of list should be 1");

        assertEquals(2, responseList.getData().get(0).getData().getId(), "Id of list should be 2");
        assertEquals(3, responseList.getData().get(1).getData().getId(), "Id of list should be 3");
        assertEquals(attachmentId2, responseList.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void listStringCommentsTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(
                project3Id,
                null,
                null,
                null,
                null,
                null,
                null,
                Collections.singletonList(orderById)
        );
        assertNotNull(responseList);
        assertNotNull(responseList.getData());
        assertEquals(2, responseList.getData().size(), "Size of list should be 1");

        assertEquals(3, responseList.getData().get(0).getData().getId(), "Id of list should be 3");
        assertEquals(2, responseList.getData().get(1).getData().getId(), "Id of list should be 2");
        assertEquals(attachmentId2, responseList.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void addStringCommentTest() {
        Attachment attachment = new Attachment();
        attachment.setId(attachmentId);
        List<Attachment> attachments = Collections.singletonList(attachment);

        AddStringCommentRequest request = new AddStringCommentRequest() {{
            setText(text);
            setTargetLanguageId(targetLanguageId);
            setStringId(stringId);
            setType(type);
            setIssueType(issueType);
            setAttachments(attachments);
        }};
        ResponseObject<StringComment> response = this.getStringCommentsApi().addStringComment(projectId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

        assertEquals(attachmentId2, response.getData().getAttachments().get(0).getId());
    }

    @Test
    public void addFileCommentTest() {
        AddStringCommentRequest request = new AddStringCommentRequest();
        request.setFileId(fileId);
        request.setText("Please check this file");
        request.setTargetLanguageId(targetLanguageId);
        request.setType(Type.COMMENT);
        request.setIsShared(false);

        ResponseObject<StringComment> response = this.getStringCommentsApi().addStringComment(projectId, request);
        StringComment comment = response.getData();
        assertEquals(3L, comment.getId());
        assertNull(comment.getStringId());
        assertEquals(fileId, comment.getFileId());
        assertEquals(fileId, comment.getFile().getId());
        assertEquals("index.html", comment.getFile().getName());
        assertEquals("html", comment.getFile().getType());
        assertEquals("Main page", comment.getFile().getContext());
        assertFalse(comment.getIsShared());
        assertNull(comment.getResolver());
    }

    @Test
    public void listStringCommentsByFileIdTest() {
        ResponseList<StringComment> responseList = this.getStringCommentsApi().listStringComments(
                project4Id, null, fileId, null, null, Type.COMMENT, null, null, null);
        assertEquals(1, responseList.getData().size());
        StringComment comment = responseList.getData().get(0).getData();
        assertEquals(project4Id, comment.getProjectId());
        assertEquals(fileId, comment.getFileId());
        assertEquals("index.html", comment.getFile().getName());
    }

    @Test
    public void getStringCommentTest() {
        ResponseObject<StringComment> response = this.getStringCommentsApi().getStringComment(projectId, stringCommentId);
        assertNotNull(response);
        assertNotNull(response.getData());

        StringComment comment = response.getData();
        assertEquals(attachmentId2, comment.getAttachments().get(0).getId());
        assertEquals(projectId, comment.getProjectId());
        assertEquals(13L, comment.getResolverId());
        assertEquals("jane_doe", comment.getResolver().getUsername());
        assertNotNull(comment.getResolvedAt());
        assertTrue(comment.getIsShared());
        assertEquals(1L, comment.getSenderOrganization().getId());
        assertEquals("acme", comment.getSenderOrganization().getDomain());
        assertEquals(2L, comment.getResolverOrganization().getId());
        assertEquals("vendor", comment.getResolverOrganization().getDomain());
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

        assertEquals(attachmentId2, response.getData().getAttachments().get(0).getId());
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
        assertEquals(attachmentId2, response.getData().get(0).getData().getAttachments().get(0).getId());
    }

    @Test
    public void deleteAttachmentFromStringCommentTest() {
        this.getStringCommentsApi().deleteAttachmentFromStringComment(projectId, stringCommentId, attachmentId);
    }
}
