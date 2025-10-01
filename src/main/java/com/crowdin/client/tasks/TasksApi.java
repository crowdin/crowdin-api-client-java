package com.crowdin.client.tasks;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.tasks.model.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TasksApi extends CrowdinApi {
    public TasksApi(Credentials credentials) {
        super(credentials);
    }

    public TasksApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param status filter by status
     * @param assigneeId filter by assignee id
     * @return list of tasks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Task> listTasks(Long projectId, Integer limit, Integer offset, Status status, Integer assigneeId) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status != null ? status.to(status) : null),
                "assigneeId", Optional.ofNullable(assigneeId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TaskResponseList taskResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks", new HttpRequestConfig(queryParams), TaskResponseList.class);
        return TaskResponseList.to(taskResponseList);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param status filter by status
     * @param assigneeId filter by assignee id
     * @param orderBy list of OrderByField
     * @return list of tasks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Task> listTasks(Long projectId, Integer limit, Integer offset, Status status, Integer assigneeId, List<OrderByField> orderBy) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status),
                "assigneeId", Optional.ofNullable(assigneeId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "orderBy",  Optional.ofNullable(OrderByField.generateSortParam(orderBy))
        );
        TaskResponseList taskResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks", new HttpRequestConfig(queryParams), TaskResponseList.class);
        return TaskResponseList.to(taskResponseList);
    }


    /**
     * Lists tasks for a given project, filtered by multiple statuses.
     *
     * @param projectId project identifier
     * @param params Query params
     * @return List of tasks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Task> listTasks(Long projectId, ListTasksParams params) throws HttpException, HttpBadRequestException {
        ListTasksParams query = Optional.ofNullable(params).orElse(new ListTasksParams());

        EnumSet<Status> statuses = query.getStatuses();

        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(
                        statuses == null ? null : statuses.stream()
                                .map(status -> status.to(status))
                                .collect(Collectors.joining(","))
                ),
                "assigneeId", Optional.ofNullable(query.getAssigneeId()),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset())
        );

        TaskResponseList taskResponseList = this.httpClient.get(
                this.url + "/projects/" + projectId + "/tasks",
                new HttpRequestConfig(queryParams),
                TaskResponseList.class
        );

        return TaskResponseList.to(taskResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created task
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Task> addTask(Long projectId, AddTaskRequest request) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/tasks", request, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @return url for task strings
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.exports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.exports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> exportTaskStrings(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/exports", null, new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @return task
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Task> getTask(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks/" + taskId, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTask(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/tasks/" + taskId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param request request object
     * @return updated task
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Task> editTask(Long projectId, Long taskId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/tasks/" + taskId, request, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param status filter by status
     * @param isArchived filter by archived status
     * @return list of user tasks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.user.tasks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.user.tasks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Task> listUserTasks(Integer limit, Integer offset, Status status, BooleanInt isArchived) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "isArchived", Optional.ofNullable(isArchived)
        );
        TaskResponseList taskResponseList = this.httpClient.get(this.url + "/user/tasks", new HttpRequestConfig(queryParams), TaskResponseList.class);
        return TaskResponseList.to(taskResponseList);
    }

    /**
     * @param taskId task identifier
     * @param projectId project identifier (filter)
     * @param request request object (only for archived flag)
     * @return updated archived status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.user.tasks.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.user.tasks.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Task> editTaskArchivedStatus(Long taskId, Long projectId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId)
        );
        TaskResponseObject taskResponseObject = this.httpClient.patch(this.url + "/user/tasks/" + taskId, request, new HttpRequestConfig(queryParams), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    //<editor-fold desc="Task Settings Templates">

    /**
     * @param projectId project identifier (filter)
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of task settings templates
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.settings-templates.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.settings-templates.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<TaskSettingsTemplate> listTaskSettingsTemplates(Long projectId, Integer limit, Integer offset) {
        String url = formUrl_taskSettingsTemplates(projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TaskSettingsTemplateResponseList responseList = this.httpClient.get(url, new HttpRequestConfig(queryParams), TaskSettingsTemplateResponseList.class);
        return TaskSettingsTemplateResponseList.to(responseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created task settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.settings-templates.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.settings-templates.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskSettingsTemplate> addTaskSettingsTemplate(Long projectId, AddTaskSettingsTemplateRequest request) {
        String url = formUrl_taskSettingsTemplates(projectId);
        TaskSettingsTemplateResponseObject responseObject = this.httpClient.post(url, request, new HttpRequestConfig(), TaskSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskSettingsTemplateId task settings template identifier
     * @return task settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.settings-templates.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.settings-templates.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskSettingsTemplate> getTaskSettingsTemplate(Long projectId, Long taskSettingsTemplateId) {
        String url = formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId);
        TaskSettingsTemplateResponseObject responseObject = this.httpClient.get(url, new HttpRequestConfig(), TaskSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskSettingsTemplateId task settings template identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.settings-templates.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.settings-templates.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTaskSettingsTemplate(Long projectId, Long taskSettingsTemplateId) {
        String url = formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId);
        this.httpClient.delete(url, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param taskSettingsTemplateId task settings template identifier
     * @param request request object
     * @return updated task settings template
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.settings-templates.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.settings-templates.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskSettingsTemplate> editTaskSettingsTemplate(Long projectId, Long taskSettingsTemplateId, List<PatchRequest> request) {
        String url = formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId);
        TaskSettingsTemplateResponseObject responseObject = this.httpClient.patch(url, request, new HttpRequestConfig(), TaskSettingsTemplateResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of task comments
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<TaskComment> listTasksComments(Long projectId, Long taskId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TaskCommentResponseList taskCommentResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments", new HttpRequestConfig(queryParams), TaskCommentResponseList.class);
        return TaskCommentResponseList.to(taskCommentResponseList);
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param request request object
     * @return newly created task comment
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskComment> addTaskComment(Long projectId, Long taskId, CreateTaskCommentRequest request) throws HttpException, HttpBadRequestException {
        TaskCommentResponseObject taskCommentResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments", request, new HttpRequestConfig(), TaskCommentResponseObject.class);
        return ResponseObject.of(taskCommentResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param commentId comment identifier
     * @return task comment
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskComment> getTaskComment(Long projectId, Long taskId, Long commentId) throws HttpException, HttpBadRequestException {
        TaskCommentResponseObject taskCommentResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments/" + commentId, new HttpRequestConfig(), TaskCommentResponseObject.class);
        return ResponseObject.of(taskCommentResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param commentId task identifier
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTaskComment(Long projectId, Long taskId, Long commentId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments/" + commentId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param taskId task identifier
     * @param commentId task identifier
     * @param request request object
     * @return updated task comment
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Tasks/operation/api.projects.tasks.comments.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TaskComment> editTaskComment(Long projectId, Long taskId, Long commentId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TaskCommentResponseObject taskCommentResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/comments/" + commentId, request, new HttpRequestConfig(), TaskCommentResponseObject.class);
        return ResponseObject.of(taskCommentResponseObject.getData());
    }

    //<editor-fold desc="Helper methods">

    private String formUrl_taskSettingsTemplates(Long projectId) {
        return this.url + "/projects/" + projectId + "/tasks/settings-templates";
    }

    private String formUrl_taskSettingsTemplateId(Long projectId, Long taskSettingsTemplateId) {
        return this.url + "/projects/" + projectId + "/tasks/settings-templates/" + taskSettingsTemplateId;
    }

    //</editor-fold>

    //</editor-fold>
}
