package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.projectsgroups.model.AddGroupRequest;
import com.crowdin.client.projectsgroups.model.AddProjectRequest;
import com.crowdin.client.projectsgroups.model.Group;
import com.crowdin.client.projectsgroups.model.GroupResponseList;
import com.crowdin.client.projectsgroups.model.GroupResponseObject;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectResponseList;
import com.crowdin.client.projectsgroups.model.ProjectResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProjectsGroupsApi extends CrowdinApi {

    public ProjectsGroupsApi(Credentials credentials) {
        super(credentials);
    }

    public ProjectsGroupsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param parentId parent group identifier. Get via List Groups
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of groups
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Group> listGroups(Long parentId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "parentId", Optional.ofNullable(parentId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        GroupResponseList groupResponseList = this.httpClient.get(this.url + "/groups", new HttpRequestConfig(queryParams), GroupResponseList.class);
        return GroupResponseList.to(groupResponseList);
    }

    /**
     * @param request request object
     * @return newly created group
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Group> addGroup(AddGroupRequest request) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.post(this.url + "/groups", request, new HttpRequestConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId group identifier
     * @return group
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Group> getGroup(Long groupId) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.get(this.url + "/groups/" + groupId, new HttpRequestConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId group identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteGroup(Long groupId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/groups/" + groupId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param groupId group identifier
     * @param request request object
     * @return updated group
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Group> editGroup(Long groupId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        GroupResponseObject groupResponseObject = this.httpClient.patch(this.url + "/groups/" + groupId, request, new HttpRequestConfig(), GroupResponseObject.class);
        return ResponseObject.of(groupResponseObject.getData());
    }

    /**
     * @param groupId group identifier (optional)
     * @param hasManagerAccess projects with manager access (default 0)
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of projects
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<? extends Project> listProjects(Long groupId, Integer hasManagerAccess, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "hasManagerAccess", Optional.ofNullable(hasManagerAccess),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ProjectResponseList projectResponseList = this.httpClient.get(this.url + "/projects", new HttpRequestConfig(queryParams), ProjectResponseList.class);
        return ProjectResponseList.to(projectResponseList);
    }

    /**
     * @param request request object
     * @return newly created project
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<? extends Project> addProject(AddProjectRequest request) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.post(this.url + "/projects", request, new HttpRequestConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @return project
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<? extends Project> getProject(Long projectId) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.get(this.url + "/projects/" + projectId, new HttpRequestConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteProject(Long projectId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return updated project
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<? extends Project> editProject(Long projectId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        ProjectResponseObject projectResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId, request, new HttpRequestConfig(), ProjectResponseObject.class);
        return ResponseObject.of(projectResponseObject.getData());
    }
}
