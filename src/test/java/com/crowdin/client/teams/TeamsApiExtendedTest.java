package com.crowdin.client.teams;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.SortOrder;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.teams.model.*;
import com.crowdin.client.users.model.ProjectPermissions;
import com.crowdin.client.users.model.TranslatorRoleName;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class TeamsApiExtendedTest extends TestClient {

    private final Long projectId = 12L;
    private final Long project2Id = 13L;
    private final Long teamId = 1L;
    private final Long groupId = 27L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/teams/" + teamId, HttpGet.METHOD_NAME, "api/teams/team.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/teams", HttpGet.METHOD_NAME, "api/teams/listProjectTeams.json", new HashMap<String, Object>() {{
                    put("orderBy", "id%20desc");
                    put("limit", 25);
                    put("offset", 0);
                }}),
                RequestMock.build(this.url + "/projects/" + project2Id + "/teams", HttpGet.METHOD_NAME, "api/teams/listProjectTeams.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/teams", HttpPost.METHOD_NAME, "api/teams/addTeamToProjectManagerRequest.json", "api/teams/projectTeamResourcesUpdated.json"),
                RequestMock.build(this.url + "/teams/" + teamId + "/projects/permissions", HttpGet.METHOD_NAME, "api/teams/teamProjectPermissions.json", new HashMap<String, Object>() {{
                    put("limit", 25);
                }}),
                RequestMock.build(this.url + "/teams/" + teamId + "/projects/permissions", HttpPatch.METHOD_NAME, "api/teams/editTeamProjectPermissionsRequest.json", "api/teams/teamProjectPermissions.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/teams", HttpGet.METHOD_NAME, "api/teams/listGroupTeams.json", new HashMap<String, Object>() {{
                    put("limit", 10);
                    put("offset", 5);
                }})
        );
    }

    @Test
    public void getTeamWebUrlTest() {
        Team team = this.getTeamsApi().getTeam(teamId).getData();
        assertEquals(teamId, team.getId());
        assertEquals("https://example.crowdin.com/u/teams/1", team.getWebUrl());
    }

    @Test
    public void listProjectTeamsTest() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(SortOrder.DESC);

        ListProjectTeamsParams params = new ListProjectTeamsParams();
        params.setOrderBy(singletonList(orderBy));
        params.setLimit(25);
        params.setOffset(0);

        ResponseList<ProjectTeamResource> response = this.getTeamsApi().listProjectTeams(projectId, params);
        assertEquals(1, response.getData().size());
        ProjectTeamResource team = response.getData().get(0).getData();
        assertEquals(teamId, team.getId());
        assertEquals("French", team.getName());
        assertTrue(team.isHasDeveloperAccess());
        assertEquals(TranslatorRoleName.TRANSLATOR, team.getRoles().get(0).getName());
        assertTrue(team.getRoles().get(0).getPermissions().getLanguagesAccess().get("it").isAllContent());
    }

    @Test
    public void listProjectTeamsWithoutParamsTest() {
        ResponseList<ProjectTeamResource> response = this.getTeamsApi().listProjectTeams(project2Id, null);
        assertEquals(1, response.getData().size());
    }

    @Test
    public void addTeamToProjectUpdatedTest() {
        AddTeamToProjectRequest request = new AddTeamToProjectRequest();
        request.setTeamId(teamId);
        request.setManagerAccess(true);

        ProjectTeamResources response = this.getTeamsApi().addTeamToProject(projectId, request);
        assertNotNull(response.getUpdated());
        assertEquals(teamId, response.getUpdated().getId());
        assertEquals("French", response.getUpdated().getName());
        assertTrue(response.getUpdated().isHasManagerAccess());
    }

    @Test
    public void listTeamProjectPermissionsTest() {
        ResponseList<ProjectPermissions> response = this.getTeamsApi().listTeamProjectPermissions(teamId, 25, null);
        assertTeamProjectPermissions(response);
    }

    @Test
    public void editTeamProjectPermissionsTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REMOVE);
        request.setPath("/" + projectId + "/roles");

        ResponseList<ProjectPermissions> response = this.getTeamsApi().editTeamProjectPermissions(teamId, singletonList(request));
        assertTeamProjectPermissions(response);
    }

    private void assertTeamProjectPermissions(ResponseList<ProjectPermissions> response) {
        assertEquals(1, response.getData().size());
        ProjectPermissions permissions = response.getData().get(0).getData();
        assertEquals(projectId, permissions.getId());
        assertEquals(TranslatorRoleName.PROOFREADER, permissions.getRoles().get(0).getName());
        assertTrue(permissions.getRoles().get(0).getPermissions().isAllLanguages());
        assertEquals(projectId, permissions.getProject().getId());
        assertEquals("Knowledge Base", permissions.getProject().getName());
        assertNull(permissions.getTeams());
    }

    @Test
    public void listGroupTeamsWithPaginationTest() {
        ListGroupTeamsParams params = new ListGroupTeamsParams(groupId);
        params.setLimit(10);
        params.setOffset(5);

        ResponseList<GroupTeam> response = this.getTeamsApi().listGroupTeams(params);
        assertEquals(1, response.getData().size());
        assertEquals(teamId, response.getData().get(0).getData().getTeam().getId());
    }
}
