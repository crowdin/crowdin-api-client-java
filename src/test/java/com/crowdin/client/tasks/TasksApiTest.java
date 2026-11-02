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
    private final Long batchProjectId = 19L;
    private final Long userId = 7L;
    private final Date updatedDate = new Date(1569222014000L);
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
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/vendorTaskRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/EnterpriseTaskCreateFormRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/enterpriseStringsBasedTask.json", "api/tasks/enterpriseTask.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/enterprisePendingTask.json", "api/tasks/enterpriseTask.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpGet.METHOD_NAME, "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/exports", HttpPost.METHOD_NAME, "api/tasks/downloadLink.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks/" + taskId, HttpPatch.METHOD_NAME, "api/tasks/editTask.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/user/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json"),
                RequestMock.build(this.url + "/user/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
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
                }}),
                RequestMock.build(this.url + "/projects/" + batchProjectId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasks.json", new HashMap<String, Object>() {{
                    put("batchId", 7L);
                }}),
                RequestMock.build(this.url + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listAllTasks.json", new HashMap<String, Object>() {{
                    put("orderBy", "id%20desc");
                    put("limit", 10);
                    put("offset", 0);
                    put("status", "todo,in_progress");
                    put("type", "2,3");
                    put("projectIds", "1,2");
                    put("assigneeIds", "5");
                    put("creatorIds", "6");
                    put("targetLanguageIds", "fr,de");
                    put("sourceLanguageIds", "en");
                    put("createdAtFrom", "2024-01-01T00:00:00Z");
                    put("createdAtTo", "2024-02-01T00:00:00Z");
                    put("deadlineFrom", "2024-03-01T00:00:00Z");
                    put("deadlineTo", "2024-04-01T00:00:00Z");
                }}),
                RequestMock.build(this.url + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listAllTasks.json", new HashMap<String, Object>() {{
                    put("groupIds", "3");
                }}),
                RequestMock.build(this.url + "/users/" + userId + "/tasks", HttpGet.METHOD_NAME, "api/tasks/listAllTasks.json", new HashMap<String, Object>() {{
                    put("projectIds", "1");
                    put("type", "0");
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addTaskNewFieldsRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addTaskFileBasedNewFieldsRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addVendorStringsBasedTaskNewFieldsRequest.json", "api/tasks/task.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addEnterpriseTaskNewFieldsRequest.json", "api/tasks/enterpriseTask.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addEnterpriseVendorStringsBasedTaskNewFieldsRequest.json", "api/tasks/enterpriseTask.json"),
                RequestMock.build(this.url + "/projects/" + enterpriseProjectId + "/tasks", HttpPost.METHOD_NAME, "api/tasks/addEnterprisePendingVendorTaskRequest.json", "api/tasks/enterpriseTask.json")
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
    public void listTasksByBatchIdTest() {
        ListTasksParams params = new ListTasksParams();
        params.setBatchId(7L);
        ResponseList<Task> taskResponseList = this.getTasksApi().listTasks(batchProjectId, params);
        assertNotNull(taskResponseList.getData().get(0).getData());
        assertEquals(1, taskResponseList.getData().size());
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
        request.setBatchId(99L);
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
    public void addTaskVendorTest() {
        CreateTaskVendorRequest request = new CreateTaskVendorRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setFileIds(singletonList(1L));
        request.setType(TypeVendor.TRANSLATE_BY_VENDOR);
        request.setVendor("oht");
        request.setSkipAssignedStrings(true);
        request.setIncludePreTranslatedStringsOnly(true);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskResponseObject.getData().getId(), taskId);
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
        assertEquals(30, taskResponseObject.getData().getOriginalWordsCount());
        assertEquals(10, taskResponseObject.getData().getSyncScope().getSyncedWords());
        assertEquals(5, taskResponseObject.getData().getSyncScope().getPendingWords());
        assertEquals(2, taskResponseObject.getData().getSyncScope().getSkippedWords());
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
    public void listUserTasksTest_orderByNull() {
        ResponseList<Task> taskResponseList = this.getTasksApi().listUserTasks(null, null, null, null, null);
        assertEquals(1, taskResponseList.getData().size());
        assertEquals(taskId, taskResponseList.getData().get(0).getData().getId());
        assertEquals(status, taskResponseList.getData().get(0).getData().getStatus());
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

    @Test
    public void listOrganizationTasksTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(SortOrder.DESC);

        ListAllTasksParams params = new ListAllTasksParams();
        params.setOrderBy(singletonList(orderBy));
        params.setLimit(10);
        params.setOffset(0);
        params.setStatuses(EnumSet.of(Status.TODO, Status.IN_PROGRESS));
        params.setTypes(EnumSet.of(Type.TRANSLATE_BY_VENDOR, Type.PROOFREAD_BY_VENDOR));
        params.setProjectIds(Arrays.asList(1L, 2L));
        params.setAssigneeIds(singletonList(5L));
        params.setCreatorIds(singletonList(6L));
        params.setTargetLanguageIds(Arrays.asList("fr", "de"));
        params.setSourceLanguageIds(singletonList("en"));
        params.setCreatedAtFrom("2024-01-01T00:00:00Z");
        params.setCreatedAtTo("2024-02-01T00:00:00Z");
        params.setDeadlineFrom("2024-03-01T00:00:00Z");
        params.setDeadlineTo("2024-04-01T00:00:00Z");

        ResponseList<Task> taskResponseList = this.getTasksApi().listOrganizationTasks(params);
        assertEquals(4, taskResponseList.getData().size());

        Task vendorTask = taskResponseList.getData().get(0).getData();
        assertEquals(30L, vendorTask.getId());
        assertEquals(Type.TRANSLATE_BY_VENDOR, vendorTask.getType());
        assertEquals("alconost", vendorTask.getVendor());
        assertEquals(4L, vendorTask.getBatchId());
        assertEquals(LabelMatchRule.ANY, vendorTask.getLabelMatchRule());
        assertEquals(LabelMatchRule.ALL, vendorTask.getExcludeLabelMatchRule());
        assertEquals("2025-06-23T09:04:29+00:00|2025-08-23T09:04:29+00:00", vendorTask.getTranslationsUpdatedTimeRange());
        assertEquals(24.12, vendorTask.getEstimatedCost().getCost());
        assertEquals("USD", vendorTask.getEstimatedCost().getCurrency());
        assertEquals(new Date(119, Calendar.SEPTEMBER, 23, 9, 4, 29), vendorTask.getEstimatedCost().getDate());
        assertEquals(vendorTask.getEstimatedCost(), vendorTask.getActualCost());
        assertEquals(Boolean.TRUE, vendorTask.getGenerateCostEstimate());
        assertEquals(Boolean.FALSE, vendorTask.getGenerateTranslationCost());
        assertEquals(3L, vendorTask.getReportSettingsTemplateId());
        assertEquals(singletonList(5L), vendorTask.getBranchIds());
        assertEquals(Boolean.FALSE, vendorTask.getIsArchived());
        assertEquals(3600L, vendorTask.getAssignees().get(0).getTimeSpent());
        assertEquals(1800L, vendorTask.getAssignedTeams().get(0).getTimeSpent());

        Task proofreadByVendorTask = taskResponseList.getData().get(1).getData();
        assertEquals(Type.PROOFREAD_BY_VENDOR, proofreadByVendorTask.getType());
        assertNull(proofreadByVendorTask.getEstimatedCost());
        assertNull(proofreadByVendorTask.getLabelMatchRule());
        assertNull(proofreadByVendorTask.getReportSettingsTemplateId());

        assertEquals(Type.TRANSLATE, taskResponseList.getData().get(2).getData().getType());
        assertEquals(Type.PROOFREAD, taskResponseList.getData().get(3).getData().getType());
    }

    @Test
    public void listOrganizationTasksByGroupIdsTest() {
        ListAllTasksParams params = new ListAllTasksParams();
        params.setGroupIds(singletonList(3L));
        ResponseList<Task> taskResponseList = this.getTasksApi().listOrganizationTasks(params);
        assertEquals(4, taskResponseList.getData().size());
    }

    @Test
    public void listUserProjectTasksTest() {
        ListAllTasksParams params = new ListAllTasksParams();
        params.setProjectIds(singletonList(1L));
        params.setTypes(EnumSet.of(Type.TRANSLATE));
        // Enterprise only, must not be sent to the Crowdin endpoint
        params.setGroupIds(singletonList(3L));
        ResponseList<Task> taskResponseList = this.getTasksApi().listUserProjectTasks(userId, params);
        assertEquals(4, taskResponseList.getData().size());
        assertEquals(Type.TRANSLATE_BY_VENDOR, taskResponseList.getData().get(0).getData().getType());
    }

    @Test
    public void typeFromTest() {
        assertEquals(Type.TRANSLATE, Type.from("0"));
        assertEquals(Type.PROOFREAD, Type.from("1"));
        assertEquals(Type.TRANSLATE_BY_VENDOR, Type.from("2"));
        assertEquals(Type.PROOFREAD_BY_VENDOR, Type.from("3"));
        assertNull(Type.from("4"));
        assertEquals(TypeVendor.TRANSLATE_BY_VENDOR, TypeVendor.from("2"));
        assertEquals(TypeVendor.PROOFREAD_BY_VENDOR, TypeVendor.from("3"));
        assertNull(TypeVendor.from("0"));
    }

    @Test
    public void addTaskWithNewFieldsTest() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setType(Type.PROOFREAD);
        request.setBranchIds(singletonList(3L));
        request.setLabelIds(singletonList(1L));
        request.setLabelMatchRule(LabelMatchRule.ANY);
        request.setExcludeLabelIds(singletonList(2L));
        request.setExcludeLabelMatchRule(LabelMatchRule.ALL);
        request.setTranslationsUpdatedDateFrom(updatedDate);
        request.setTranslationsUpdatedDateTo(updatedDate);
        request.setGenerateCostEstimate(true);
        request.setGenerateTranslationCost(true);
        request.setReportSettingsTemplateId(5L);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
    }

    @Test
    public void addTaskWithDirectoryIdsTest() {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setType(Type.TRANSLATE);
        request.setDirectoryIds(singletonList(4L));
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
    }

    @Test
    public void addVendorStringsBasedTaskWithLabelsTest() {
        CreateTaskVendorStringsBasedRequest request = new CreateTaskVendorStringsBasedRequest();
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setStringIds(singletonList(1L));
        request.setType(TypeVendor.PROOFREAD_BY_VENDOR);
        request.setVendor("alconost");
        request.setLabelIds(singletonList(1L));
        request.setLabelMatchRule(LabelMatchRule.ANY);
        request.setExcludeLabelIds(singletonList(2L));
        request.setExcludeLabelMatchRule(LabelMatchRule.ANY);
        request.setGenerateCostEstimate(true);
        request.setReportSettingsTemplateId(5L);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(projectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
    }

    @Test
    public void addEnterpriseTaskWithNewFieldsTest() {
        CreateTaskEnterpriseRequest request = new CreateTaskEnterpriseRequest();
        request.setWorkflowStepId(10L);
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setDirectoryIds(singletonList(4L));
        request.setSkipAssignedStrings(true);
        request.setSkipAssignedStringsScope(SkipAssignedStringsScope.SAME_WORKFLOW_STEP);
        request.setLabelIds(singletonList(1L));
        request.setLabelMatchRule(LabelMatchRule.ALL);
        request.setGenerateTranslationCost(true);
        request.setReportSettingsTemplateId(5L);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
    }

    @Test
    public void addEnterpriseVendorStringsBasedTaskWithLabelsTest() {
        CreateTaskEnterpriseVendorStringsBasedRequest request = new CreateTaskEnterpriseVendorStringsBasedRequest();
        request.setWorkflowStepId(11L);
        request.setTitle("French");
        request.setLanguageId("fr");
        request.setStringIds(singletonList(1L));
        request.setLabelIds(singletonList(1L));
        request.setLabelMatchRule(LabelMatchRule.ANY);
        request.setSkipAssignedStrings(true);
        request.setSkipAssignedStringsScope(SkipAssignedStringsScope.ALL);
        request.setTranslationsUpdatedDateFrom(updatedDate);
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
    }

    @Test
    public void addEnterprisePendingVendorTaskTest() {
        CreateEnterprisePendingTaskRequest request = new CreateEnterprisePendingTaskRequest();
        request.setPrecedingTaskId(prevTaskId);
        request.setType(Type.PROOFREAD_BY_VENDOR);
        request.setWorkflowStepId(12L);
        request.setTitle("French");
        request.setVendor("alconost");
        ResponseObject<Task> taskResponseObject = this.getTasksApi().addTask(enterpriseProjectId, request);
        assertEquals(taskId, taskResponseObject.getData().getId());
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
