package com.crowdin.client.tasks;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.*;
import com.crowdin.client.tasks.model.pending.CreateEnterprisePendingTaskRequest;
import com.crowdin.client.tasks.model.pending.CreatePendingTaskRequest;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TasksApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long enterpriseProjectId = 13L;
    private final Long multiStatusProjectId = 14L;
    private final Long singleStatusProjectId = 15L;
    private final Long tasksProjectIdSortByIdAsc = 16L;
    private final Long tasksProjectIdSortByIdDesc = 17L;
    private final Long tasksProjectIdSortByIdDescTitleAsc = 18L;
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
                RequestMock.build(this.url + "/user/tasks/" + taskId, HttpPatch.METHOD_NAME, "api/tasks/editTask.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + multiStatusProjectId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/multiStatusListTasks.json", new HashMap<String, String>() {{
                    put("status", "todo,done");
                }}),
                RequestMock.build(this.url + "/projects/" + singleStatusProjectId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/singleStatusListTasks.json", new HashMap<String, String>() {{
                    put("status", "in_progress");
                }}),
                RequestMock.build(this.url + "/projects/" + tasksProjectIdSortByIdAsc + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasksSortByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/projects/" + tasksProjectIdSortByIdDesc + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasksSortByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/projects/" + tasksProjectIdSortByIdDescTitleAsc + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasksSortByIdDescTitleAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc%2Ctitle%20asc");
                }})
        );
    }

    @Test
    public void listTasksTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(projectId, null, null, null, null);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(1, taskResponseList.getData().size());

        assertEquals(projectId, taskResponseList.getData().get(0).getData().getProjectId());

        assertListTasks(taskResponseList);
    }

    @Test
    public void listTasksTest_noSortDefined() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(projectId, null, null, null, null, null);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(1, taskResponseList.getData().size());

        assertEquals(projectId, taskResponseList.getData().get(0).getData().getProjectId());

        assertListTasks(taskResponseList);
    }

    @Test
    public void listTasksTest_testSortByIdDefault() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");

        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(orderBy);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(tasksProjectIdSortByIdAsc, null, null, null, null, orderByFields);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(2, taskResponseList.getData().size());

        assertEquals(1, taskResponseList.getData().get(0).getData().getId());
        assertEquals(2, taskResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTasksTest_testSortByIdAsc() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(SortOrder.ASC);

        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(orderBy);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(tasksProjectIdSortByIdAsc, null, null, null, null, orderByFields);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(2, taskResponseList.getData().size());

        assertEquals(1, taskResponseList.getData().get(0).getData().getId());
        assertEquals(2, taskResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTasksTest_testSortByIdDesc() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(SortOrder.DESC);

        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(orderBy);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(tasksProjectIdSortByIdDesc, null, null, null, null, orderByFields);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(2, taskResponseList.getData().size());

        assertEquals(2, taskResponseList.getData().get(0).getData().getId());
        assertEquals(1, taskResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listTasksTest_testSortByIdDescTitleAsc() {
        OrderByField orderByIdDesc = new OrderByField();
        orderByIdDesc.setFieldName("id");
        orderByIdDesc.setOrderBy(SortOrder.DESC);

        OrderByField orderByTitleNull = new OrderByField();
        orderByTitleNull.setFieldName("title");

        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(orderByIdDesc);
        orderByFields.add(orderByTitleNull);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(tasksProjectIdSortByIdDescTitleAsc, null, null, null, null, orderByFields);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(2, taskResponseList.getData().size());

        assertEquals(2, taskResponseList.getData().get(0).getData().getId());
        assertNotNull(taskResponseList.getData().get(0).getData().getTitle());
        assertEquals("French#1", taskResponseList.getData().get(0).getData().getTitle());


        assertEquals(1, taskResponseList.getData().get(1).getData().getId());
        assertNotNull(taskResponseList.getData().get(1).getData().getTitle());
        assertEquals("French#2", taskResponseList.getData().get(1).getData().getTitle());
    }

    @Test
    public void listTasksTest_testSingleStatus() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(singleStatusProjectId, null, null, Status.IN_PROGRESS, null);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(1, taskResponseList.getData().size());
        assertEquals(singleStatusProjectId, taskResponseList.getData().get(0).getData().getProjectId());
        assertEquals(Status.IN_PROGRESS, taskResponseList.getData().get(0).getData().getStatus());

        assertListTasks(taskResponseList);
    }

    @Test
    void listTasksTest_withNullStatuses() {
        ListTasksParams listTasksParams = new ListTasksParams();
        listTasksParams.setStatuses(null);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(projectId, listTasksParams);

        assertNotNull(taskResponseList);
        assertEquals(1, taskResponseList.getData().size());
        assertEquals(projectId, taskResponseList.getData().get(0).getData().getProjectId());

        assertListTasks(taskResponseList);
    }

    @Test
    public void listTasksTest_multipleStatuses() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        EnumSet<Status> statuses = EnumSet.of(Status.TODO, Status.DONE);

        ListTasksParams listTasksParams = new ListTasksParams();
        listTasksParams.setStatuses(statuses);

        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(multiStatusProjectId, listTasksParams);

        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(1, taskResponseList.getData().size());
        assertEquals(multiStatusProjectId, taskResponseList.getData().get(0).getData().getProjectId());

        Status responseProjectStatus = taskResponseList.getData().get(0).getData().getStatus();
        assertTrue(statuses.contains(responseProjectStatus));

        assertListTasks(taskResponseList);
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

    //<editor-fold desc="Common method for listTasks assertion">
    @SneakyThrows
    private void assertListTasks(ResponseList<Task> taskResponseList) {
        Assignee assignee = new Assignee();
        assignee.setId(1L);
        assignee.setUsername("john_smith");
        assignee.setFullName("john_smith");
        assignee.setAvatarUrl("");
        assignee.setWordsCount(5);
        assignee.setWordsLeft(3);

        assertEquals(taskId, taskResponseList.getData().get(0).getData().getId());
        assertEquals(new Date(119, Calendar.SEPTEMBER,27,7,0,14), taskResponseList.getData().get(0).getData().getDeadline());
        assertEquals(assignee, taskResponseList.getData().get(0).getData().getAssignees().get(0));
    }
    //</editor-fold>
}
