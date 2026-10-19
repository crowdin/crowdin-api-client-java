package com.crowdin.client.users;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.teams.model.GroupTeam;
import com.crowdin.client.users.model.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class UsersApiExtendedTest extends TestClient {

    private final Long userId = 1L;
    private final Long projectId = 8L;
    private final Long memberId = 4L;
    private final Long groupId = 27L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/users/" + userId, HttpGet.METHOD_NAME, "api/users/userEnterpriseDetails.json"),
                RequestMock.build(this.url + "/user", HttpPatch.METHOD_NAME, "api/users/editAuthenticatedUserRequest.json", "api/users/authenticatedUserCrowdin.json"),
                RequestMock.build(this.url + "/users", HttpPost.METHOD_NAME, "api/users/inviteUserAdminRequest.json", "api/users/userEnterpriseDetails.json"),
                RequestMock.build(this.url + "/users/" + userId + "/projects/permissions", HttpGet.METHOD_NAME, "api/users/userProjectPermissions.json", new HashMap<String, Object>() {{
                    put("limit", 25);
                    put("offset", 0);
                }}),
                RequestMock.build(this.url + "/users/" + userId + "/projects/permissions", HttpPatch.METHOD_NAME, "api/users/editProjectPermissionsRequest.json", "api/users/userProjectPermissions.json"),
                RequestMock.build(this.url + "/users/" + userId + "/projects/contributions", HttpGet.METHOD_NAME, "api/users/userProjectContributions.json", new HashMap<String, Object>() {{
                    put("limit", 10);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpPost.METHOD_NAME, "api/users/addProjectMemberCrowdinRequest.json", "api/users/addProjectMemberCrowdinResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembers.json", new HashMap<String, Object>() {{
                    put("search", "john");
                    put("role", "translator");
                    put("languageId", "uk");
                    put("orderBy", "id%20desc");
                    put("limit", 25);
                    put("offset", 0);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/members/" + memberId, HttpPut.METHOD_NAME, "api/users/replaceProjectMemberPermissionsDeveloperRequest.json", "api/users/replaceProjectMemberPermissionsResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members/" + memberId, HttpGet.METHOD_NAME, "api/users/projectMemberEnterpriseAdmin.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/managers", HttpGet.METHOD_NAME, "api/users/listGroupManagers.json", new HashMap<String, Object>() {{
                    put("limit", 25);
                    put("offset", 0);
                }})
        );
    }

    @Test
    public void getUserEnterpriseDetailsTest() {
        User user = this.getUsersApi().getUser(userId).getData();
        assertEquals(userId, user.getId());
        assertTrue(user.getEmailVerified());
        assertEquals("some value 1", user.getFields().get("some-field-1"));
        assertEquals(12, user.getFields().get("some-field-2"));
        assertEquals("invite", user.getJoinDetails().getType());
        assertEquals(2L, user.getJoinDetails().getInvitedBy().getId());
        assertEquals("jane_doe", user.getJoinDetails().getInvitedBy().getUsername());
        assertEquals("Jane Doe", user.getJoinDetails().getInvitedBy().getFullName());
        assertEquals("https://example.com/avatar.png", user.getJoinDetails().getInvitedBy().getAvatarUrl());
        assertEquals("enabled", user.getDeviceVerification());
        assertEquals(2, user.getTrustedDevicesCount());
        assertEquals(3, user.getApiTokensCount());
        assertEquals(Arrays.asList("password", "google"), user.getLoginMethods());
        assertEquals(singletonList("totp"), user.getMfaMethods());
    }

    @Test
    public void editAuthenticatedUserTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/fullName");
        request.setValue("John Smith");

        ResponseObject<User> response = this.getUsersApi().editAuthenticatedUser(singletonList(request));
        assertEquals(userId, response.getData().getId());
        assertEquals("John Smith", response.getData().getFullName());
        assertTrue(response.getData().getEmailVerified());
    }

    @Test
    public void inviteUserWithAdminAccessTest() {
        InviteUserRequest request = new InviteUserRequest();
        request.setEmail("john@example.com");
        request.setFirstName("John");
        request.setLastName("Smith");
        request.setTimezone("Europe/Kyiv");
        request.setAdminAccess(true);

        ResponseObject<User> response = this.getUsersApi().inviteUser(request);
        assertEquals(userId, response.getData().getId());
    }

    @Test
    public void listUserProjectPermissionsTest() {
        ResponseList<ProjectPermissions> response = this.getUsersApi().listUserProjectPermissions(userId, 25, 0);
        assertEquals(1, response.getData().size());
        assertProjectPermissions(response.getData().get(0).getData());
    }

    @Test
    public void editUserProjectPermissionsTest() {
        Map<String, Object> permissions = new HashMap<>();
        permissions.put("allLanguages", true);
        Map<String, Object> role = new HashMap<>();
        role.put("name", "translator");
        role.put("permissions", permissions);

        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/" + projectId + "/roles");
        request.setValue(singletonList(role));

        ResponseList<ProjectPermissions> response = this.getUsersApi().editUserProjectPermissions(userId, singletonList(request));
        assertEquals(1, response.getData().size());
        assertProjectPermissions(response.getData().get(0).getData());
    }

    private void assertProjectPermissions(ProjectPermissions permissions) {
        assertEquals(projectId, permissions.getId());
        assertEquals(TranslatorRoleName.TRANSLATOR, permissions.getRoles().get(0).getName());
        assertEquals(singletonList(882), permissions.getRoles().get(0).getPermissions().getLanguagesAccess().get("it").getWorkflowStepIds());
        Project project = permissions.getProject();
        assertEquals(projectId, project.getId());
        assertEquals(4L, project.getGroupId());
        assertEquals(1, permissions.getTeams().size());
        assertEquals(2L, permissions.getTeams().get(0).getId());
        assertEquals("https://example.crowdin.com/u/teams/2", permissions.getTeams().get(0).getWebUrl());
    }

    @Test
    public void listUserProjectContributionsTest() {
        ResponseList<UserProjectContribution> response = this.getUsersApi().listUserProjectContributions(userId, 10, null);
        assertEquals(1, response.getData().size());
        UserProjectContribution contribution = response.getData().get(0).getData();
        assertEquals(projectId, contribution.getId());
        assertEquals(1L, contribution.getTranslated().getStrings());
        assertEquals(2L, contribution.getTranslated().getWords());
        assertEquals(3L, contribution.getApproved().getStrings());
        assertEquals(4L, contribution.getApproved().getWords());
        assertEquals(5L, contribution.getVoted().getStrings());
        assertNull(contribution.getVoted().getWords());
        assertEquals(6L, contribution.getCommented().getStrings());
        assertEquals(projectId, contribution.getProject().getId());
        assertEquals("Knowledge Base", contribution.getProject().getName());
    }

    @Test
    public void addProjectMemberCrowdinTest() {
        AddProjectMemberRequest request = new AddProjectMemberRequest();
        // legacy comma-separated setter must produce a JSON array on the wire
        request.setUsernames("john_smith, jane_doe");
        request.setManagerAccess(false);
        request.setDeveloperAccess(true);
        request.setMessage("Welcome to the project");

        assertEquals(Arrays.asList("john_smith", "jane_doe"), request.getUsernameList());
        assertEquals("john_smith,jane_doe", request.getUsernames());

        ProjectMembersResponse response = this.getUsersApi().addProjectMember(projectId, request);
        assertEquals(1, response.getAdded().size());
        assertEquals(userId, response.getAdded().get(0).getData().getId());
        assertEquals(1, response.getUpdated().size());
        assertEquals(3L, response.getUpdated().get(0).getData().getId());
        assertEquals("proofreader", response.getUpdated().get(0).getData().getPermissions().get("uk").getRole());

        ProjectMember skipped = response.getSkipped().get(0).getData();
        assertEquals("translator", skipped.getRole());
        assertEquals("translator", skipped.getPermissions().get("uk").getRole());
        assertEquals("proofreader", skipped.getPermissions().get("it").getRole());
        assertEquals("denied", skipped.getPermissions().get("en").getRole());
        assertNull(skipped.getPermissions().get("uk").getWorkflowStepIds());
        assertTrue(skipped.getRoles().get(0).getPermissions().isAllLanguages());
    }

    @Test
    public void addProjectMemberWithUsernameListTest() {
        AddProjectMemberRequest request = new AddProjectMemberRequest();
        request.setUsernameList(new ArrayList<>(Arrays.asList("john_smith", "jane_doe")));
        request.setManagerAccess(false);
        request.setDeveloperAccess(true);
        request.setMessage("Welcome to the project");

        ProjectMembersResponse response = this.getUsersApi().addProjectMember(projectId, request);
        assertEquals(1, response.getAdded().size());
    }

    @Test
    public void listProjectMembersWithParamsTest() {
        com.crowdin.client.core.model.OrderByField orderBy = new com.crowdin.client.core.model.OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(com.crowdin.client.core.model.SortOrder.DESC);

        ListProjectMembersParams params = new ListProjectMembersParams();
        params.setSearch("john");
        params.setRole("translator");
        params.setLanguageId("uk");
        params.setOrderBy(singletonList(orderBy));
        params.setLimit(25);
        params.setOffset(0);

        ResponseList<TeamMember> response = this.getUsersApi().listProjectMembers(projectId, params);
        assertEquals(1, response.getData().size());
        assertEquals(userId, response.getData().get(0).getData().getId());
    }

    @Test
    public void replaceProjectMemberPermissionsWithDeveloperAccessTest() {
        ReplaceProjectMemberPermissionsRequest request = new ReplaceProjectMemberPermissionsRequest();
        request.setManagerAccess(false);
        request.setDeveloperAccess(true);
        request.setRoles(Collections.singletonList(new TranslatorRole() {{
            setName(TranslatorRoleName.TRANSLATOR);
            setPermissions(new TranslatorRolePermissions() {{
                setAllLanguages(true);
            }});
        }}));

        ResponseObject<ProjectMember> response = this.getUsersApi().replaceProjectMemberPermissions(projectId, memberId, request);
        assertNotNull(response.getData());
    }

    @Test
    public void getProjectMemberIsAdminTest() {
        ProjectMember member = this.getUsersApi().getProjectMemberPermissions(projectId, memberId).getData();
        assertEquals(userId, member.getId());
        assertTrue(member.getIsAdmin());
        assertTrue(member.isDeveloper());
        assertNull(member.getPermissions());
    }

    @Test
    public void listGroupManagersWithPaginationTest() {
        ListGroupManagersParams params = new ListGroupManagersParams();
        params.setLimit(25);
        params.setOffset(0);

        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(groupId, params);
        GroupManager manager = response.getData().get(0).getData();
        assertEquals(1, manager.getTeamList().size());
        assertEquals(2L, manager.getTeamList().get(0).getId());
        assertEquals("Translators Team", manager.getTeamList().get(0).getName());
        assertEquals(8, manager.getTeamList().get(0).getTotalMembers());

        // deprecated accessor derives GroupTeam wrappers from the plain team objects
        List<GroupTeam> legacyTeams = manager.getTeams();
        assertEquals(1, legacyTeams.size());
        assertEquals(2L, legacyTeams.get(0).getId());
        assertEquals("Translators Team", legacyTeams.get(0).getTeam().getName());
    }
}
