package com.crowdin.client.users;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.users.model.AddProjectMemberRequest;
import com.crowdin.client.users.model.ProjectMember;
import com.crowdin.client.users.model.ProjectMemberResponseObject;
import com.crowdin.client.users.model.ProjectMembersResponse;
import com.crowdin.client.users.model.ReplaceProjectMemberPermissionsRequest;
import com.crowdin.client.users.model.Status;
import com.crowdin.client.users.model.TeamMember;
import com.crowdin.client.users.model.TeamMemberResponseList;
import com.crowdin.client.users.model.TeamMemberResponseObject;
import com.crowdin.client.users.model.TwoFactor;
import com.crowdin.client.users.model.User;
import com.crowdin.client.users.model.UserResponseList;
import com.crowdin.client.users.model.UserResponseObject;

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
     * @param request request object
     * @return project team members response info
     */
    public ProjectMembersResponse addProjectMember(Long projectId, AddProjectMemberRequest request) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/projects/" + projectId + "/members", request, new HttpRequestConfig(), ProjectMembersResponse.class);
    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @return project member info
     */
    public ResponseObject<ProjectMember> getProjectMember(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        ProjectMemberResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ProjectMemberResponseObject.class);
        return ResponseObject.of(response.getData());

    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     * @param request request object
     * @return updated project member info
     */
    public ResponseObject<ProjectMember> replaceProjectMemberPermissions(Long projectId, Long memberId, ReplaceProjectMemberPermissionsRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        ProjectMemberResponseObject response = this.httpClient.put(builtUrl, request, new HttpRequestConfig(), ProjectMemberResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param memberId member identifier
     */
    public void deleteMemberFromProject(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/projects/%d/members/%d", this.url, projectId, memberId);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param status    filter by status
     * @param search    search users by firstName, lastName, username, or email (2 or more characters)
     * @param twoFactor filter by two-factor authentication status
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of users
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
     * @param userId user identifier
     * @return user
     */
    public ResponseObject<User> getUser(Long userId) throws HttpException, HttpBadRequestException {
        UserResponseObject languageResponseObject = this.httpClient.get(this.url + "/users/" + userId, new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }

    /**
     * @return current authenticated user
     */
    public ResponseObject<User> getAuthenticatedUser() throws HttpException, HttpBadRequestException {
        UserResponseObject languageResponseObject = this.httpClient.get(this.url + "/user", new HttpRequestConfig(), UserResponseObject.class);
        return ResponseObject.of(languageResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param search    search users by firstName, lastName, username, or email (2 or more characters)
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of team members
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
     * @param memberId  member identifier
     * @return team member
     */
    public ResponseObject<TeamMember> getMemberInfo(Long projectId, Long memberId) throws HttpException, HttpBadRequestException {
        TeamMemberResponseObject teamMemberResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/members/" + memberId, new HttpRequestConfig(), TeamMemberResponseObject.class);
        return ResponseObject.of(teamMemberResponseObject.getData());
    }
}
