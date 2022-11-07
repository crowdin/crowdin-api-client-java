package com.crowdin.client.users;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.teams.model.TeamResponseObject;
import com.crowdin.client.users.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersApi extends CrowdinApi {
    public UsersApi(Credentials credentials) {
        super(credentials);
    }

    public UsersApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * List project members. For Crowdin Enterprise only
     * @param projectId Project Identifier. Get via List Projects
     * @param search Search users by firstName, lastName or username
     * @param languageId Language Identifier. Get via Project Target Languages
     * @param workflowStepId Workflow Step Identifier. Get via List Workflow Steps
     * @param limit A maximum number of items to retrieve
     * @param offset A starting offset in the collection
     * @return list of project members
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ProjectMember> listProjectMembersEnterprise(Long projectId, String search, String languageId, Long workflowStepId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members", this.url, projectId);
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
            "search", Optional.ofNullable(search),
            "languageId", Optional.ofNullable(languageId),
            "workflowStepId", Optional.ofNullable(workflowStepId),
            "limit", Optional.ofNullable(limit),
            "offset", Optional.ofNullable(offset)
        );
        ProjectMemberResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ProjectMemberResponseList.class);
        return ProjectMemberResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return project team members response info
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ProjectMembersResponse addProjectMember(Long projectId, AddProjectMemberRequest request) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/projects/" + projectId + "/members", request, new HttpRequestConfig(), ProjectMembersResponse.class);
    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @return project member info
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ProjectMember> getProjectMemberPermissions(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        ProjectMemberResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ProjectMemberResponseObject.class);
        return ResponseObject.of(response.getData());

    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @param request request object
     * @return updated project member info
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ProjectMember> replaceProjectMemberPermissions(Long projectId, Long memberId, ReplaceProjectMemberPermissionsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        ProjectMemberResponseObject response = this.httpClient.put(builtUrl, request, new HttpRequestConfig(), ProjectMemberResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteMemberFromProject(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param status filter by status
     * @param search search users by firstName, lastName, username, or email (2 or more characters)
     * @param twoFactor filter by two-factor authentication status
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of users
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<User> listUsers(Status status, String search, TwoFactor twoFactor, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "status", Optional.ofNullable(status),
                "search", Optional.ofNullable(search),
                "twoFactor", Optional.ofNullable(twoFactor),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        UserResponseList userResponseList = this.httpClient.get(this.url + "/users", new HttpRequestConfig(queryParams), UserResponseList.class);
        return UserResponseList.to(userResponseList);
    }

    /**
     * @param request request object
     * @return invited user
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<User> inviteUser(InviteUserRequest request) throws HttpException, HttpBadRequestException {
        UserResponseObject invitedUserResponseObject = this.httpClient.post(this.url + "/users", request, new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(invitedUserResponseObject.getData());
    }

    /**
     * @param userId user identifier
     * @return user
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.getById" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<User> getUser(Long userId) throws HttpException, HttpBadRequestException {
        UserResponseObject languageResponseObject = this.httpClient.get(this.url + "/users/" + userId, new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }

    /**
     * @param userId user identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteUser(Long userId) throws HttpException, HttpBadRequestException{
        this.httpClient.delete(this.url + "/users/" + userId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param userId  user identifier
     * @param request request object
     * @return updated user
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<User> editUser(Long userId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        UserResponseObject editedUserResponseObject = this.httpClient.patch(this.url + "/users/" + userId, request, new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(editedUserResponseObject.getData());
    }

    /**
     * @return current authenticated user
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.user.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.user.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<User> getAuthenticatedUser() throws HttpException, HttpBadRequestException {
        UserResponseObject languageResponseObject = this.httpClient.get(this.url + "/user", new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param search search users by firstName, lastName, username, or email (2 or more characters)
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of team members
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.members.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<TeamMember> listProjectMembers(Long projectId, String search, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "search", Optional.ofNullable(search),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TeamMemberResponseList teamMemberResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/members", new HttpRequestConfig(queryParams), TeamMemberResponseList.class);
        return TeamMemberResponseList.to(teamMemberResponseList);
    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @return team member
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.members.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<TeamMember> getMemberInfo(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        TeamMemberResponseObject teamMemberResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/members/" + memberId, new HttpRequestConfig(), TeamMemberResponseObject.class);
        return ResponseObject.of(teamMemberResponseObject.getData());
    }
}
