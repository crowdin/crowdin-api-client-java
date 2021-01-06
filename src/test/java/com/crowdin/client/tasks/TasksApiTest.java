package com.crowdin.client.tasks;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.AddTaskRequest;
import com.crowdin.client.tasks.model.CrowdinTaskCreateFormRequest;
import com.crowdin.client.tasks.model.EnterpriseTaskCreateFormRequest;
import com.crowdin.client.tasks.model.Status;
import com.crowdin.client.tasks.model.Task;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long enterpriseProjectId = 13L;
    private final Long taskId = 2L;
    private final Status status = Status.TODO;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/CrowdinTaskCreateFormRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/EnterpriseTaskCreateFormRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpGet.METHOD_NAME, "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/exports", HttpPost.METHOD_NAME, "api/tasks/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpPatch.METHOD_NAME, "api/tasks/editTask.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/user/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json"),
                RequestMock.build(this.url + "/user/tasks/" + taskId, HttpPatch.METHOD_NAME, "api/tasks/editTask.json", "api/tasks/task.json")
        );
    }

    @Test
    public void listTasksTest() {
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(projectId, null, null, null);
        assertEquals(taskResponseList.getData().size(), 1);
        assertEquals(taskResponseList.getData().get(0).getData().getId(), taskId);
        assertEquals(taskResponseList.getData().get(0).getData().getStatus(), status);
    }

    @Test
    public void addTaskTest() {
        CrowdinTaskCreateFormRequest request = new CrowdinTaskCreateFormRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setFileIds(singletonList(1L));
        request.setStatus(Status.TODO);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }

    @Test
    public void addTaskEnterpriseTest() {
        EnterpriseTaskCreateFormRequest request = new EnterpriseTaskCreateFormRequest();
        request.setWorkflowStepId(0L);
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setFileIds(singletonList(1L));
        request.setStatus(Status.TODO);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }

    @Test
    public void exportTaskStringsTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTasksApi().exportTaskStrings(projectId, taskId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void getTaskTest() {
        ResponseObject<Task> taskResponseObject = this.getTasksApi().getTask(projectId, taskId);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }

    @Test
    public void deleteTaskTest() {
        this.getTasksApi().deleteTask(projectId, taskId);
    }

    @Test
    public void editTaskTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(Status.TODO);
        request.setPath("/status");
        ResponseObject<Task> taskResponseObject = this.getTasksApi().editTask(projectId, taskId, singletonList(request));
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }

    @Test
    public void listUserTasksTest() {
        ResponseList<Task> taskResponseList = this.getTasksApi().listUserTasks(null, null, null, null);
        assertEquals(taskResponseList.getData().size(), 1);
        assertEquals(taskResponseList.getData().get(0).getData().getId(), taskId);
        assertEquals(taskResponseList.getData().get(0).getData().getStatus(), status);
    }

    @Test
    public void editUserTaskTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(Status.TODO);
        request.setPath("/status");
        ResponseObject<Task> taskResponseObject = this.getTasksApi().editTaskArchivedStatus(taskId, null, singletonList(request));
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }
}
