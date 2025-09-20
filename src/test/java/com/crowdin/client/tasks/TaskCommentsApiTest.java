package com.crowdin.client.tasks;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.*;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskCommentsApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long taskId = 203L;
    private final Long taskCommentId = 1233L;
    private final Long userId = 5L;

    private TimeZone originalTimeZone;

    private static final Instant EXPECTED_DATE = Instant.parse("2025-08-23T09:04:29Z");

    @BeforeEach
    void setUp() {
        originalTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }

    @AfterEach
    void tearDown() {
        TimeZone.setDefault(originalTimeZone);
    }

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        formUrl_taskComments(projectId, taskId),
                        HttpGet.METHOD_NAME,
                        "api/tasks/comments/listTaskCommentsResponse.json",
                        new HashMap<String, Integer>() {{
                            put("limit", 20);
                            put("offset", 10);
                        }}
                ),

                // ADD
                RequestMock.build(
                        formUrl_taskComments(projectId, taskId),
                        HttpPost.METHOD_NAME,
                        "api/tasks/comments/addTaskCommentRequest.json",
                        "api/tasks/comments/taskCommentsResponse_single.json"
                ),

                // GET
                RequestMock.build(
                        formUrl_taskCommentId(projectId, taskId, taskCommentId),
                        HttpGet.METHOD_NAME,
                        "api/tasks/comments/taskCommentsResponse_single.json"
                ),

                // DELETE
                RequestMock.build(
                        formUrl_taskCommentId(projectId, taskId, taskCommentId),
                        HttpDelete.METHOD_NAME
                ),

                // PATCH
                RequestMock.build(
                        formUrl_taskCommentId(projectId, taskId, taskCommentId),
                        HttpPatch.METHOD_NAME,
                        "api/tasks/comments/editTaskCommentTextRequest.json",
                        "api/tasks/comments/taskCommentsResponse_single.json"
                )
        );
    }

    @Test
    @SneakyThrows
    public void listTaskCommentsTest() {
        ResponseList<TaskComment> response = this.getTasksApi().listTasksComments(projectId, taskId, 20, 10);

        assertNotNull(response.getData().get(0).getData());
        assertEquals(1, response.getData().size());

        TaskComment comment = response.getData().get(0).getData();

        assertTaskComment(comment);
    }

    @Test
    @SneakyThrows
    public void addTaskCommentTest() {
        CreateTaskCommentRequest request = new CreateTaskCommentRequest();
        request.setText("translate task");
        request.setTimeSpent(3600L);

        ResponseObject<TaskComment> response = this.getTasksApi().addTaskComment(projectId, taskId, request);

        assertNotNull(response.getData());

        TaskComment comment = response.getData();

        assertTaskComment(comment);
    }

    @Test
    @SneakyThrows
    public void getTaskCommentTest() {
        ResponseObject<TaskComment> response = this.getTasksApi().getTaskComment(projectId, taskId, taskCommentId);

        assertNotNull(response.getData());

        TaskComment comment = response.getData();

        assertTaskComment(comment);
    }

    @Test
    @SneakyThrows
    public void editTaskCommentTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue("translate task");
        request.setPath("/text");
        ResponseObject<TaskComment> response = this.getTasksApi().editTaskComment(projectId, taskId, taskCommentId, singletonList(request));

        assertNotNull(response.getData());

        TaskComment comment = response.getData();

        assertTaskComment(comment);
    }

    @Test
    @SneakyThrows
    public void deleteTaskCommentTest() {
        assertDoesNotThrow(() ->
                this.getTasksApi().deleteTaskComment(projectId, taskId, taskCommentId)
        );
    }

    @SneakyThrows
    private void assertTaskComment(TaskComment comment) {
        assertEquals(taskCommentId, comment.getId());
        assertEquals(taskId, comment.getTaskId());
        assertEquals(userId, comment.getUserId());
        assertEquals("translate task", comment.getText());
        assertEquals(3600L, comment.getTimeSpent());

        assertNotNull(comment.getCreatedAt());
        assertNotNull(comment.getUpdatedAt());
        assertEquals(EXPECTED_DATE, comment.getCreatedAt().toInstant());
        assertEquals(EXPECTED_DATE, comment.getUpdatedAt().toInstant());
    }

    //<editor-fold desc="Form Url methods for mocks">
    private String formUrl_taskComments(Long projectId, Long taskId) {
        return this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments";
    }

    private String formUrl_taskCommentId(Long projectId, Long taskId, Long taskCommentId) {
        return this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments/" + taskCommentId;
    }
    //</editor-fold>
}
