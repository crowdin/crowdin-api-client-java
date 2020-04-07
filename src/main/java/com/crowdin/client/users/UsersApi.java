package com.crowdin.client.users;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.users.model.AddProjectTeamMemberRequest;
import com.crowdin.client.users.model.ProjectTeamMembersResponse;
import com.crowdin.client.users.model.Status;
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
    public ProjectTeamMembersResponse addProjectTeamMember(Long projectId, AddProjectTeamMemberRequest request) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/projects/" + projectId + "/members", request, new HttpRequestConfig(), ProjectTeamMembersResponse.class);
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
}
