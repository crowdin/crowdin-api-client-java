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
import com.crowdin.client.tasks.model.Status;
import com.crowdin.client.tasks.model.Task;
import com.crowdin.client.tasks.model.TaskResponseList;
import com.crowdin.client.tasks.model.TaskResponseObject;

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
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @param status    filter by status
     * @return list of tasks
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
     * @param request   request object
     * @return newly created task
     */
    public ResponseObject<Task> addTask(Long projectId, AddTaskRequest request) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/tasks", request, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId    task identifier
     * @return url for task strings
     */
    public ResponseObject<DownloadLink> exportTaskStrings(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject downloadLinkResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks/" + taskId + "/export", new HttpRequestConfig(), DownloadLinkResponseObject.class);
        return ResponseObject.of(downloadLinkResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId    task identifier
     * @return task
     */
    public ResponseObject<Task> getTask(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/tasks/" + taskId, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param taskId    task identifier
     */
    public void deleteTask(Long projectId, Long taskId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/tasks/" + taskId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param taskId    task identifier
     * @param request   request object
     * @return updated task
     */
    public ResponseObject<Task> editTask(Long projectId, Long taskId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TaskResponseObject taskResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/tasks/" + taskId, request, new HttpRequestConfig(), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }

    /**
     * @param limit      maximum number of items to retrieve (default 25)
     * @param offset     starting offset in the collection (default 0)
     * @param status     filter by status
     * @param isArchived filter by archived status
     * @return list of user tasks
     */
    public ResponseList<Task> listUserTasks(Integer limit, Integer offset, Status status, BooleanInt isArchived) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "isArchived", Optional.ofNullable(isArchived)
        );
        TaskResponseList taskResponseList = this.httpClient.get(this.url + "/user/tasks/", new HttpRequestConfig(queryParams), TaskResponseList.class);
        return TaskResponseList.to(taskResponseList);
    }

    /**
     * @param taskId    task identifier
     * @param projectId project identifier (filter)
     * @param request   request object (only for archived flag)
     * @return updated archived status
     */
    public ResponseObject<Task> editTaskArchivedStatus(Long taskId, Long projectId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "projectId", Optional.ofNullable(projectId)
        );
        TaskResponseObject taskResponseObject = this.httpClient.patch(this.url + "/user/tasks/" + taskId, request, new HttpRequestConfig(queryParams), TaskResponseObject.class);
        return ResponseObject.of(taskResponseObject.getData());
    }
}
