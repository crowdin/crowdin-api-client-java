package com.crowdin.client.tasks;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.BooleanInt;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.DownloadLinkResponseObject;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.tasks.model.AddTaskRequest;
import com.crowdin.client.tasks.model.AddTaskSettingsTemplateRequest;
import com.crowdin.client.tasks.model.Status;
import com.crowdin.client.tasks.model.Task;
import com.crowdin.client.tasks.model.TaskResponseList;
import com.crowdin.client.tasks.model.TaskResponseObject;
import com.crowdin.client.tasks.model.TaskSettingsTemplate;
import com.crowdin.client.tasks.model.TaskSettingsTemplateResponseList;
import com.crowdin.client.tasks.model.TaskSettingsTemplateResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     * @return list of tasks
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.tasks.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Task> listTasks(Long projectId, Integer limit, Integer offset, Status status) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TaskResponseList taskResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks", new HttpRequestConfig(queryParams), TaskResponseList.class);
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
