package com.crowdin.client.teams;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.teams.model.AddTeamMembersRequest;
import com.crowdin.client.teams.model.AddTeamMembersResponse;
import com.crowdin.client.teams.model.AddTeamMembersResponseInternal;
import com.crowdin.client.teams.model.AddTeamRequest;
import com.crowdin.client.teams.model.AddTeamToProjectRequest;
import com.crowdin.client.teams.model.ProjectTeamResources;
import com.crowdin.client.teams.model.Team;
import com.crowdin.client.teams.model.TeamMember;
import com.crowdin.client.teams.model.TeamMemberResponseList;
import com.crowdin.client.teams.model.TeamResponseList;
import com.crowdin.client.teams.model.TeamResponseObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TeamsApi extends CrowdinApi {
    public TeamsApi(Credentials credentials) {
        super(credentials);
    }

    public TeamsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return project team status
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.teams.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ProjectTeamResources addTeamToProject(Long projectId, AddTeamToProjectRequest request) throws HttpException, HttpBadRequestException {
        return this.httpClient.post(this.url + "/projects/" + projectId + "/teams", request, new HttpRequestConfig(), ProjectTeamResources.class);
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Team> listTeams(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TeamResponseList teamResponseList = this.httpClient.get(this.url + "/teams", new HttpRequestConfig(queryParams), TeamResponseList.class);
        return TeamResponseList.to(teamResponseList);
    }

    /**
     * @param request request object
     * @return newly created team
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Team> addTeam(AddTeamRequest request) throws HttpException, HttpBadRequestException {
        TeamResponseObject teamResponseObject = this.httpClient.post(this.url + "/teams", request, new HttpRequestConfig(), TeamResponseObject.class);
        return ResponseObject.of(teamResponseObject.getData());
    }

    /**
     * @param teamId team identifier
     * @return team
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Team> getTeam(Long teamId) throws HttpException, HttpBadRequestException {
        TeamResponseObject teamResponseObject = this.httpClient.get(this.url + "/teams/" + teamId, new HttpRequestConfig(), TeamResponseObject.class);
        return ResponseObject.of(teamResponseObject.getData());
    }

    /**
     * @param teamId team identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTeam(Long teamId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/teams/" + teamId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param teamId team identifier
     * @param request request object
     * @return updated team
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Team> editTeam(Long teamId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TeamResponseObject teamResponseObject = this.httpClient.patch(this.url + "/teams/" + teamId, request, new HttpRequestConfig(), TeamResponseObject.class);
        return ResponseObject.of(teamResponseObject.getData());
    }

    /**
     * @param teamId team identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of team members
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.members.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<TeamMember> listTeamMembers(Long teamId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TeamMemberResponseList teamMemberResponseList = this.httpClient.get(this.url + "/teams/" + teamId + "/members", new HttpRequestConfig(queryParams), TeamMemberResponseList.class);
        return TeamMemberResponseList.to(teamMemberResponseList);
    }

    /**
     * @param teamId team identifier
     * @param request request object
     * @return response
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.members.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public AddTeamMembersResponse addTeamMembers(Long teamId, AddTeamMembersRequest request) throws HttpException, HttpBadRequestException {
        AddTeamMembersResponseInternal addTeamMembersResponseInternal = this.httpClient.post(this.url + "/teams/" + teamId + "/members", request, new HttpRequestConfig(), AddTeamMembersResponseInternal.class);
        return AddTeamMembersResponseInternal.to(addTeamMembersResponseInternal);
    }

    /**
     * @param teamId team identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.members.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteAllTeamMembers(Long teamId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/teams/" + teamId + "/members", new HttpRequestConfig(), Void.class);
    }

    /**
     * @param teamId team identifier
     * @param memberId member identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.members.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTeamMember(Long teamId, Long memberId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/teams/" + teamId + "/members/" + memberId, new HttpRequestConfig(), Void.class);
    }
}
