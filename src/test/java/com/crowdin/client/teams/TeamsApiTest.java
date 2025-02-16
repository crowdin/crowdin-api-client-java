package com.crowdin.client.teams;

import com.crowdin.client.core.model.LanguageAccessRule;
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
import com.crowdin.client.teams.model.GroupTeam;
import com.crowdin.client.users.model.TranslatorRole;
import com.crowdin.client.users.model.TranslatorRoleName;
import com.crowdin.client.users.model.TranslatorRolePermissions;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;

public class TeamsApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long userId = 3L;
    private final Long teamId = 1L;
    private final Long groupId = 27L;
    private final String name = "French";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/groups/" + groupId + "/teams", HttpGet.METHOD_NAME, "api/teams/listGroupTeams.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/teams", HttpPatch.METHOD_NAME, "api/teams/editGroupTeams.json", "api/teams/listGroupTeams.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/teams/" + teamId, HttpGet.METHOD_NAME, "api/teams/groupTeam.json"),
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
    public void listGroupTeamsTest() {
        ResponseList<GroupTeam> response = this.getTeamsApi().listGroupTeams(groupId, null);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(teamId, response.getData().get(0).getData().getTeam().getId());
        assertEquals(name, response.getData().get(0).getData().getTeam().getName());
    }

    @Test
    public void updateGroupTeamsTest() {
        PatchRequest requestAdd = new PatchRequest();
        requestAdd.setOp(PatchOperation.ADD);
        requestAdd.setPath("/-");
        requestAdd.setValue(singletonMap("teamId", 1));

        PatchRequest requestRemove = new PatchRequest();
        requestRemove.setOp(PatchOperation.REMOVE);
        requestRemove.setPath("/24");

        List<PatchRequest> requests = Arrays.asList(requestAdd, requestRemove);
        ResponseList<GroupTeam> response = this.getTeamsApi().updateGroupTeams(groupId, requests);
        assertNotNull(response);
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(teamId, response.getData().get(0).getData().getTeam().getId());
    }

    @Test
    public void getGroupTeamTest() {
        ResponseObject<GroupTeam> response = this.getTeamsApi().getGroupTeam(groupId, teamId);
        assertNotNull(response);
        assertEquals(groupId, response.getData().getId());
        assertEquals(teamId, response.getData().getTeam().getId());
        assertEquals(name, response.getData().getTeam().getName());
    }

    @Test
    public void addTeamToProjectTest() {
        AddTeamToProjectRequest request = new AddTeamToProjectRequest();
        request.setTeamId(teamId);
        request.setRoles(Collections.singletonList(new TranslatorRole() {{
            setName(TranslatorRoleName.TRANSLATOR);
            setPermissions(new TranslatorRolePermissions(){{
                setAllLanguages(false);
                setLanguagesAccess(new HashMap<String, LanguageAccessRule>() {{
                    put("uk", new LanguageAccessRule(){{
                        setAllContent(false);
                        setWorkflowStepIds(Collections.singletonList(882));
                    }});
                    put("it", new LanguageAccessRule(){{
                        setAllContent(true);
                    }});
                }});
            }});
        }}));
        ProjectTeamResources projectTeamResources = this.getTeamsApi().addTeamToProject(projectId, request);
        assertEquals(projectTeamResources.getAdded().getId(), teamId);

        TranslatorRole responseRole = projectTeamResources.getAdded().getRoles().get(0);
        assertEquals(responseRole.getName(), TranslatorRoleName.TRANSLATOR);
        assertFalse(responseRole.getPermissions().isAllLanguages());

        Map<String, LanguageAccessRule> responseRules = responseRole.getPermissions().getLanguagesAccess();
        assertFalse(responseRules.get("uk").isAllContent());
        assertEquals(responseRules.get("uk").getWorkflowStepIds(), Collections.singletonList(882));
        assertTrue(responseRules.get("it").isAllContent());
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
