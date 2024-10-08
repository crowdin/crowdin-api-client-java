package com.crowdin.client.tasks;

import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.AssignedTeam;
import com.crowdin.client.tasks.model.Assignee;
import com.crowdin.client.tasks.model.AssigneeRequest;
import com.crowdin.client.tasks.model.CreateTaskEnterpriseStringsBasedRequest;
import com.crowdin.client.tasks.model.CreateTaskRequest;
import com.crowdin.client.tasks.model.CreateTaskEnterpriseVendorRequest;
import com.crowdin.client.tasks.model.CreateTaskStringsBasedRequest;
import com.crowdin.client.tasks.model.Progress;
import com.crowdin.client.tasks.model.Status;
import com.crowdin.client.tasks.model.Task;
import com.crowdin.client.tasks.model.Type;
import com.crowdin.client.tasks.model.pending.CreateEnterprisePendingTaskRequest;
import com.crowdin.client.tasks.model.pending.CreatePendingTaskRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long enterpriseProjectId = 13L;
    private final Long taskId = 2L;
    private final Long prevTaskId = 1L;
    private final Status status = Status.TODO;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/CrowdinTaskCreateFormRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addStringsBasedTaskRequest.json", "api/tasks/stringsBasedTask.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/pendingTaskRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/EnterpriseTaskCreateFormRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/enterpriseStringsBasedTask.json", "api/tasks/enterpriseTask.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/enterprisePendingTask.json", "api/tasks/enterpriseTask.json"),
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
        Assignee assignee = new Assignee();
        assignee.setId(1L);
        assignee.setUsername("john_smith");
        assignee.setFullName("john_smith");
        assignee.setAvatarUrl("");
        assignee.setWordsCount(5);
        assignee.setWordsLeft(3);
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(projectId, null, null, null, null);
        assertEquals(taskResponseList.getData().size(), 1);
        assertEquals(taskResponseList.getData().get(0).getData().getId(), taskId);
        assertEquals(taskResponseList.getData().get(0).getData().getStatus(), status);
        assertEquals(new Date(119, Calendar.SEPTEMBER,27,7,0,14), taskResponseList.getData().get(0).getData().getDeadline());
        assertEquals(taskResponseList.getData().get(0).getData().getAssignees().get(0), assignee);
    }

    @Test
    public void addTaskTest() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setFileIds(singletonList(1L));
        request.setStatus(Status.TODO);
        request.setIncludePreTranslatedStringsOnly(true);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
        assertEquals(taskResponseObject.getData().getProjectId(), projectId);
        assertEquals(taskResponseObject.getData().getCreatorId(), 6);
        assertEquals(taskResponseObject.getData().getType(), Type.PROOFREAD);
        assertEquals(taskResponseObject.getData().getVendor(), "gengo");
        assertEquals(taskResponseObject.getData().getTitle(), "French");
        assertEquals(taskResponseObject.getData().getFileIds().get(0), 1);
        assertEquals(taskResponseObject.getData().getSourceLanguageId(), "en");
        assertEquals(taskResponseObject.getData().getTargetLanguageId(), "fr");
        assertEquals(taskResponseObject.getData().getDescription(), "Proofread all French strings");
        assertEquals(taskResponseObject.getData().getTranslationUrl(), "/proofread/9092638ac9f2a2d1b5571d08edc53763/all/en-fr/10?task=dac37aff364d83899128e68afe0de4994");
        assertEquals(taskResponseObject.getData().getWordsCount(), 24);
        assertEquals(taskResponseObject.getData().getFilesCount(), 2);
        assertEquals(taskResponseObject.getData().getCommentsCount(), 0);
        assertEquals(taskResponseObject.getData().getTimeRange(), "string");
        assertEquals(taskResponseObject.getData().getWorkflowStepId(), 10);
        assertEquals(taskResponseObject.getData().getBuyUrl(), "https://www.paypal.com/cgi-bin/webscr?cmd=...");
        AssignedTeam assignedTeam = new AssignedTeam();
        assignedTeam.setId(1L);
        assignedTeam.setWordsCount(5);
        assertEquals(taskResponseObject.getData().getAssignedTeams().get(0), assignedTeam);
        Progress progress = new Progress();
        progress.setTotal(24);
        progress.setDone(15);
        progress.setPercent(62);
        assertEquals(taskResponseObject.getData().getProgress(), progress);
    }

    @Test
    public void addStringsBasedTaskTest() {
        CreateTaskStringsBasedRequest request = new CreateTaskStringsBasedRequest();
        Assignee expectedAssignee = new Assignee();
        expectedAssignee.setId(12L);
        expectedAssignee.setWordsCount(5);
        AssigneeRequest assigneeRequest = new AssigneeRequest();
        assigneeRequest.setId(expectedAssignee.getId());
        assigneeRequest.setWordsCount(expectedAssignee.getWordsCount());
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setType(Type.TRANSLATE);
        request.setStringIds(singletonList(2401L));
        request.setStatus(status);
        request.setDescription("Proofread all French strings");
        request.setSplitContent(true);
        request.setSkipAssignedStrings(true);
        request.setIncludePreTranslatedStringsOnly(true);
        request.setAssignees(singletonList(assigneeRequest));

        Task taskResponse = this.getTasksApi().addTask(projectId, request).getData();
        assertEquals(taskId, taskResponse.getId());
        assertEquals(status, taskResponse.getStatus());
        assertEquals(Type.TRANSLATE, taskResponse.getType());
        assertEquals(expectedAssignee.getId(), taskResponse.getAssignees().get(0).getId());
        assertEquals(expectedAssignee.getWordsCount(), taskResponse.getAssignees().get(0).getWordsCount());
    }

    @Test
    public void addTaskEnterpriseTest() {
        CreateTaskEnterpriseVendorRequest request = new CreateTaskEnterpriseVendorRequest();
        request.setWorkflowStepId(0L);
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setFileIds(singletonList(1L));
        request.setIncludePreTranslatedStringsOnly(true);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
    }

    @Test
    public void addStringsBasedEnterpriseTaskTest() {
        CreateTaskEnterpriseStringsBasedRequest request = new CreateTaskEnterpriseStringsBasedRequest();
        Map<String, Object> fields = new HashMap<>();
        fields.put("test-key", "test-value");
        request.setType(Type.PROOFREAD);
        request.setWorkflowStepId(10L);
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setStringIds(singletonList(1L));
        request.setStatus(status);
        request.setDescription("Proofread all French strings");
        request.setFields(fields);

        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);

        assertEquals(taskId, taskResponseObject.getData().getId());
        assertEquals(status, taskResponseObject.getData().getStatus());
        assertEquals(fields, taskResponseObject.getData().getFields());
    }

    @Test
    public void addPendingTaskTest() {
        CreatePendingTaskRequest request = new CreatePendingTaskRequest();
        Assignee expectedAssignee = new Assignee();
        expectedAssignee.setId(1L);
        expectedAssignee.setWordsCount(5);
        AssigneeRequest assigneeRequest = new AssigneeRequest();
        assigneeRequest.setId(expectedAssignee.getId());
        assigneeRequest.setWordsCount(expectedAssignee.getWordsCount());
        request.setPrecedingTaskId(1L);
        request.setType(Type.PROOFREAD);
        request.setTitle("French");
        request.setDescription("Proofread all French strings");
        request.setAssignees(singletonList(assigneeRequest));

        Task taskResponse = this.getTasksApi().addTask(projectId, request).getData();

        assertEquals(prevTaskId, taskResponse.getPrecedingTaskId());
        assertEquals(Type.PROOFREAD, taskResponse.getType());
        assertEquals(expectedAssignee.getId(), taskResponse.getAssignees().get(0).getId());
        assertEquals(expectedAssignee.getWordsCount(), taskResponse.getAssignees().get(0).getWordsCount());
    }

    @Test
    public void addEnterprisePendingTaskTest() {
        CreateEnterprisePendingTaskRequest request = new CreateEnterprisePendingTaskRequest();
        Assignee expectedAssignee = new Assignee();
        expectedAssignee.setId(1L);
        expectedAssignee.setWordsCount(5);
        AssigneeRequest assigneeRequest = new AssigneeRequest();
        assigneeRequest.setId(expectedAssignee.getId());
        assigneeRequest.setWordsCount(expectedAssignee.getWordsCount());
        request.setPrecedingTaskId(prevTaskId);
        request.setType(Type.PROOFREAD);
        request.setTitle("French");
        request.setDescription("Proofread all French strings");
        request.setAssignees(singletonList(assigneeRequest));
        request.setAssignedTeams(singletonList(assigneeRequest));

        Task taskResponse = this.getTasksApi().addTask(enterpriseProjectId, request).getData();

        assertEquals(prevTaskId, taskResponse.getPrecedingTaskId());
        assertEquals(Type.PROOFREAD, taskResponse.getType());
        assertEquals(expectedAssignee.getId(), taskResponse.getAssignees().get(0).getId());
        assertEquals(expectedAssignee.getWordsCount(), taskResponse.getAssignees().get(0).getWordsCount());
        assertEquals(expectedAssignee.getId(), taskResponse.getAssignedTeams().get(0).getId());
        assertEquals(expectedAssignee.getWordsCount(), taskResponse.getAssignedTeams().get(0).getWordsCount());
    }

    @Test
    public void exportTaskStringsTest() {
        ResponseObject<DownloadLink> downloadLinkResponseObject = this.getTasksApi().exportTaskStrings(projectId, taskId);
        assertEquals(downloadLinkResponseObject.getData().getUrl(), link);
    }

    @Test
    public void getTaskTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ResponseObject<Task> taskResponseObject = this.getTasksApi().getTask(projectId, taskId);
        Date createdAt = new Date(119,Calendar.SEPTEMBER,23,9,4,29);
        assertEquals(taskResponseObject.getData().getId(), taskId);
        assertEquals(taskResponseObject.getData().getStatus(), status);
        assertEquals(createdAt, taskResponseObject.getData().getCreatedAt());
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
