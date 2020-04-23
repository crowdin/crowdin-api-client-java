package com.crowdin.client.teams;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.teams.model.AddTeamMembersRequest;
import com.crowdin.client.teams.model.AddTeamMembersResponse;
import com.crowdin.client.teams.model.AddTeamRequest;
import com.crowdin.client.teams.model.AddTeamToProjectRequest;
import com.crowdin.client.teams.model.ProjectTeamResources;
import com.crowdin.client.teams.model.Team;
import com.crowdin.client.teams.model.TeamMember;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamsApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long userId = 3L;
    private final Long teamId = 1L;
    private final String name = "French";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/teams", HttpPost.METHOD_NAME, "api/teams/addTeamToProjectRequest.json", "api/teams/projectTeamResources.json"),
                RequestMock.build(this.url + "/teams", HttpGet.METHOD_NAME, "api/teams/listTeams.json"),
                RequestMock.build(this.url + "/teams", HttpPost.METHOD_NAME, "api/teams/addTeamRequest.json", "api/teams/team.json"),
                RequestMock.build(this.url + "/teams/" + teamId, HttpGet.METHOD_NAME, "api/teams/team.json"),
                RequestMock.build(this.url + "/teams/" + teamId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/teams/" + teamId, HttpPatch.METHOD_NAME, "api/teams/editTeam.json", "api/teams/team.json"),
                RequestMock.build(this.url + "/teams/" + teamId + "/members", HttpGet.METHOD_NAME, "api/teams/teamMembersList.json"),
                RequestMock.build(this.url + "/teams/" + teamId + "/members", HttpPost.METHOD_NAME, "api/teams/addTeamMembersRequest.json", "api/teams/addTeamMembersResponse.json"),
                RequestMock.build(this.url + "/teams/" + teamId + "/members", HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/teams/" + teamId + "/members/" + userId, HttpDelete.METHOD_NAME)
        );
    }

    @Test
    public void addTeamToProjectTest() {
        AddTeamToProjectRequest request = new AddTeamToProjectRequest();
        request.setTeamId(teamId);
        ProjectTeamResources projectTeamResources = this.getTeamsApi().addTeamToProject(projectId, request);
        assertEquals(projectTeamResources.getAdded().getId(), teamId);
    }

    @Test
    public void listTeamsTest() {
        ResponseList<Team> teamResponseList = this.getTeamsApi().listTeams(null, null);
        assertEquals(teamResponseList.getData().size(), 1);
        assertEquals(teamResponseList.getData().get(0).getData().getId(), teamId);
        assertEquals(teamResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addTeamTest() {
        AddTeamRequest request = new AddTeamRequest();
        request.setName(name);
        ResponseObject<Team> teamResponseObject = this.getTeamsApi().addTeam(request);
        assertEquals(teamResponseObject.getData().getId(), teamId);
        assertEquals(teamResponseObject.getData().getName(), name);
    }

    @Test
    public void getTeamTest() {
        ResponseObject<Team> teamResponseObject = this.getTeamsApi().getTeam(teamId);
        assertEquals(teamResponseObject.getData().getId(), teamId);
        assertEquals(teamResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteTeamTest() {
        this.getTeamsApi().deleteTeam(teamId);
    }

    @Test
    public void editTeamTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Team> teamResponseObject = this.getTeamsApi().editTeam(teamId, singletonList(request));
        assertEquals(teamResponseObject.getData().getId(), teamId);
        assertEquals(teamResponseObject.getData().getName(), name);
    }

    @Test
    public void listTeamMembersTest() {
        ResponseList<TeamMember> teamMemberResponseList = this.getTeamsApi().listTeamMembers(teamId, null, null);
        assertEquals(teamMemberResponseList.getData().size(), 1);
        assertEquals(teamMemberResponseList.getData().get(0).getData().getId(), userId);
    }

    @Test
    public void addTeamMembersTest() {
        AddTeamMembersRequest request = new AddTeamMembersRequest();
        request.setUserIds(singletonList(userId));
        AddTeamMembersResponse response = this.getTeamsApi().addTeamMembers(teamId, request);
        assertEquals(response.getAdded().size(), 1);
        assertEquals(response.getAdded().get(0).getData().getId(), userId);
    }

    @Test
    public void deleteAllTeamMembersTest() {
        this.getTeamsApi().deleteAllTeamMembers(teamId);
    }

    @Test
    public void deleteTeamMemberTest() {
        this.getTeamsApi().deleteTeamMember(teamId, userId);
    }
}
