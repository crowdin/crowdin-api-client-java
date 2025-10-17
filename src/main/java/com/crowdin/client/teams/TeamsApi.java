package com.crowdin.client.teams;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.teams.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamsApi extends CrowdinApi {
    public TeamsApi(Credentials credentials) {
        super(credentials);
    }

    public TeamsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * List group teams. For Crowdin Enterprise only
     * @param groupId group identifier
     * @param orderBy
     * @return list of group teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.teams.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<GroupTeam> listGroupTeams(Long groupId, String orderBy) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "orderBy", Optional.ofNullable(orderBy)
        );
        GroupTeamResponseList response = this.httpClient.get(this.url + "/groups/" + groupId + "/teams", new HttpRequestConfig(queryParams), GroupTeamResponseList.class);
        return GroupTeamResponseList.to(response);
    }

    /**
     * List group teams. For Crowdin Enterprise only
     * @param params ListGroupTeamsParams object
     * @return list of group teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.teams.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<GroupTeam> listGroupTeams(ListGroupTeamsParams params) throws HttpException, HttpBadRequestException {
        if (params == null) {
            throw new NullPointerException("params is null");
        }

        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(params.getOrderByList()))
        );

        GroupTeamResponseList response = this.httpClient.get(this.url + "/groups/" + params.getGroupId() + "/teams", new HttpRequestConfig(queryParams), GroupTeamResponseList.class);
        return GroupTeamResponseList.to(response);
    }

    /**
     * Update group teams. For Crowdin Enterprise only
     * @param groupId group identifier
     * @param request request object
     * @return list of updated group teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.teams.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<GroupTeam> updateGroupTeams(Long groupId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        GroupTeamResponseList response = this.httpClient.patch(this.url + "/groups/" + groupId + "/teams", request, new HttpRequestConfig(), GroupTeamResponseList.class);
        return GroupTeamResponseList.to(response);
    }

    /**
     * Get group team. For Crowdin Enterprise only
     * @param groupId group identifier
     * @param teamId team identifier
     * @return group team
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.groups.teams.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<GroupTeam> getGroupTeam(Long groupId, Long teamId) throws HttpException, HttpBadRequestException {
        GroupTeamResponseObject response = this.httpClient.get(this.url + "/groups/" + groupId + "/teams/" + teamId, new HttpRequestConfig(), GroupTeamResponseObject.class);
        return ResponseObject.of(response.getData());
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
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param orderBy list of OrderByField
     * @return list of teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Team> listTeams(Integer limit, Integer offset, List<OrderByField> orderBy) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(orderBy))
        );
        TeamResponseList teamResponseList = this.httpClient.get(this.url + "/teams", new HttpRequestConfig(queryParams), TeamResponseList.class);
        return TeamResponseList.to(teamResponseList);
    }

    /**
     * @param params ListTeamsParams
     * @return list of teams
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.teams.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Team> listTeams(ListTeamsParams params) throws HttpException, HttpBadRequestException {
        ListTeamsParams query = Optional.ofNullable(params).orElse(new ListTeamsParams());

        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "search", Optional.ofNullable(query.getSearch()),
                "projectIds", Optional.ofNullable(
                        query.getProjectIds() == null ? null : query.getProjectIds().stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))
                ),
                "projectRoles", Optional.ofNullable(
                        query.getProjectRoles() == null ? null : query.getProjectRoles().stream()
                                .map(projectRole -> projectRole.to(projectRole))
                                .collect(Collectors.joining(","))
                ),
                "languageIds", Optional.ofNullable(
                        query.getLanguageIds() == null ? null : String.join(",", query.getLanguageIds())
                ),
                "groupIds", Optional.ofNullable(
                        query.getGroupIds() == null ? null : query.getGroupIds().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
                ),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(query.getOrderBy())),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset())
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
