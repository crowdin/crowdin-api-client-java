package com.crowdin.client.users;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;

public class UsersApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long projectId2 = 13L;
    private final Long projectId3 = 14L;
    private final Long projectId4 = 15L;
    private final Long projectId5 = 16L;
    private final Long projectId6 = 17L;

    private final Long userId = 1L;
    private final Long user2Id = 2L;
    private final Long memberId = 3L;
    private final Long groupId = 27L;
    private final Long group2Id = 28L;
    private final Long group3Id = 29L;

    private final String name = "Smith";

    private final String email = "john@example.com";

    private final String firstName = "Jon";

    private final String lastName = "Doe";

    private final String timezone = "America/New_York";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/groups/" + groupId + "/managers", HttpGet.METHOD_NAME, "api/users/listGroupManagers.json"),
                RequestMock.build(this.url + "/groups/" + group2Id + "/managers", HttpGet.METHOD_NAME, "api/users/listGroupManagersOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/groups/" + group3Id + "/managers", HttpGet.METHOD_NAME, "api/users/listGroupManagersOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/groups/" + groupId + "/managers", HttpPatch.METHOD_NAME, "api/users/editGroupManagers.json", "api/users/listGroupManagers.json"),
                RequestMock.build(this.url + "/groups/" + groupId + "/managers/" + userId, HttpGet.METHOD_NAME, "api/users/groupManager.json"),
                RequestMock.build(String.format("%s/projects/%d/members", this.url, projectId2), HttpGet.METHOD_NAME, "api/users/listProjectMembersEnterprise.json"),
                RequestMock.build(String.format("%s/projects/%d/members", this.url, projectId3), HttpGet.METHOD_NAME, "api/users/listProjectMembersEnterpriseOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(String.format("%s/projects/%d/members", this.url, projectId4), HttpGet.METHOD_NAME, "api/users/listProjectMembersEnterpriseOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpPost.METHOD_NAME, "api/users/addProjectMember.json", "api/users/projectTeamMembers.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpGet.METHOD_NAME, "api/users/getProjectMemberResponse.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpPut.METHOD_NAME, "api/users/replaceProjectMemberPermissionsRequest.json", "api/users/replaceProjectMemberPermissionsResponse.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsers.json"),
                RequestMock.build(this.url + "/users/" + userId, HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/user", HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembers.json"),
                RequestMock.build(this.url + "/projects/" + projectId5 + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembersOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }}),
                RequestMock.build(this.url + "/projects/" + projectId6 + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembersOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }}),
                RequestMock.build(this.url + "/users", HttpPost.METHOD_NAME, "api/users/invitedUser.json", "api/users/user.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members/" + userId, HttpGet.METHOD_NAME, "api/users/projectMember.json"),
                RequestMock.build(this.url + "/users/" + userId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users/" + userId, HttpPatch.METHOD_NAME, "api/users/editUser.json", "api/users/user.json")
        );
    }

    @Test
    public void listGroupManagersTest() {
        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(groupId, null, null);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(userId, response.getData().get(0).getData().getUser().getId());
    }

    @Test
    public void listGroupManagersTest_orderByNull() {
        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(groupId, null);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(userId, response.getData().get(0).getData().getUser().getId());
    }

    @Test
    public void listGroupManagersTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ListGroupManagersParams params = new ListGroupManagersParams();
        params.setOrderBy(singletonList(orderById));

        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(group2Id, params);
        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(userId, response.getData().get(0).getData().getUser().getId());

        assertEquals(group2Id, response.getData().get(1).getData().getId());
        assertEquals(user2Id, response.getData().get(1).getData().getUser().getId());
    }

    @Test
    public void listGroupManagersTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ListGroupManagersParams params = new ListGroupManagersParams();
        params.setOrderBy(singletonList(orderById));

        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(group2Id, params);
        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(userId, response.getData().get(0).getData().getUser().getId());

        assertEquals(group2Id, response.getData().get(1).getData().getId());
        assertEquals(user2Id, response.getData().get(1).getData().getUser().getId());
    }

    @Test
    public void listGroupManagersTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ListGroupManagersParams params = new ListGroupManagersParams();
        params.setOrderBy(singletonList(orderById));

        ResponseList<GroupManager> response = this.getUsersApi().listGroupManagers(group2Id, params);
        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(group2Id, response.getData().get(1).getData().getId());
        assertEquals(user2Id, response.getData().get(1).getData().getUser().getId());

        assertEquals(groupId, response.getData().get(0).getData().getId());
        assertEquals(userId, response.getData().get(0).getData().getUser().getId());
    }

    @Test
    public void updateGroupManagersTest() {
        PatchRequest requestAdd = new PatchRequest();
        requestAdd.setOp(PatchOperation.ADD);
        requestAdd.setValue(singletonMap("userId", userId));
        requestAdd.setPath("/-");

        PatchRequest requestRemove = new PatchRequest();
        requestRemove.setOp(PatchOperation.REMOVE);
        requestRemove.setPath("/24");

        List<PatchRequest> requests = Arrays.asList(requestAdd, requestRemove);
        ResponseList<GroupManager> responseList = this.getUsersApi().updateGroupManagers(groupId, requests);
        assertEquals(1, responseList.getData().size());
        assertEquals(userId, responseList.getData().get(0).getData().getUser().getId());
    }

    @Test
    public void getGroupManagerTest() {
        ResponseObject<GroupManager> response = this.getUsersApi().getGroupManager(groupId, userId);
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals(groupId, response.getData().getId());
        assertEquals(userId, response.getData().getUser().getId());
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest() {
        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId2, null, null, null, null, null);
        assertNotNull(responseList);
        assertEquals(responseList.getData().size(), 1);
        assertEquals(responseList.getData().get(0).getData().getId(), this.userId);

        TranslatorRole role = responseList.getData().get(0).getData().getRoles().get(0);
        assertEquals(role.getName(), TranslatorRoleName.TRANSLATOR);
        assertTrue(role.getPermissions().isAllLanguages());
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest_orderByNull() {
        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId2, null, null, null, null, null, null);
        assertNotNull(responseList);
        assertEquals(1, responseList.getData().size());
        assertEquals(this.userId, responseList.getData().get(0).getData().getId());

        TranslatorRole role = responseList.getData().get(0).getData().getRoles().get(0);
        assertEquals(TranslatorRoleName.TRANSLATOR, role.getName());
        assertTrue(role.getPermissions().isAllLanguages());
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId3, null, null, null, null, null, singletonList(orderById));
        assertNotNull(responseList);
        assertEquals(2, responseList.getData().size());
        assertEquals(this.userId, responseList.getData().get(0).getData().getId());
        assertEquals(this.user2Id, responseList.getData().get(1).getData().getId());
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId3, null, null, null, null, null, singletonList(orderById));
        assertNotNull(responseList);
        assertEquals(2, responseList.getData().size());
        assertEquals(this.userId, responseList.getData().get(0).getData().getId());
        assertEquals(this.user2Id, responseList.getData().get(1).getData().getId());
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId4, null, null, null, null, null, singletonList(orderById));
        assertNotNull(responseList);
        assertEquals(2, responseList.getData().size());
        assertEquals(this.user2Id, responseList.getData().get(0).getData().getId());
        assertEquals(this.userId, responseList.getData().get(1).getData().getId());
    }

    @Test
    public void addProjectTeamMemberTest() {
        AddProjectMemberRequest request = new AddProjectMemberRequest();
        request.setUserIds(singletonList(userId));
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

        LanguagePermission languagePermission = new LanguagePermission();
        languagePermission.setWorkflowStepIds("all");
        request.setPermissions(Collections.singletonMap(
                "de", languagePermission
        ));
        ProjectMembersResponse projectMembersResponse = this.getUsersApi().addProjectMember(projectId, request);
        assertEquals(projectMembersResponse.getAdded().size(), 1);
        assertEquals(projectMembersResponse.getAdded().get(0).getData().getId(), userId);

        TranslatorRole responseRole = projectMembersResponse.getAdded().get(0).getData().getRoles().get(0);
        assertEquals(responseRole.getName(), TranslatorRoleName.TRANSLATOR);
        assertFalse(responseRole.getPermissions().isAllLanguages());

        Map<String, LanguageAccessRule> responseRules = responseRole.getPermissions().getLanguagesAccess();
        assertFalse(responseRules.get("uk").isAllContent());
        assertEquals(responseRules.get("uk").getWorkflowStepIds(), Collections.singletonList(882));
        assertTrue(responseRules.get("it").isAllContent());
    }

    @Test
    public void getProjectMemberTest() {
        ResponseObject<ProjectMember> response = this.getUsersApi().getProjectMemberPermissions(this.projectId, this.memberId);
        assertNotNull(response);
        assertNotNull(response.getData());
    }

    @Test
    public void replaceProjectMemberPermissionsTest() {
        ReplaceProjectMemberPermissionsRequest request = new ReplaceProjectMemberPermissionsRequest() {{
            setAccessToAllWorkflowSteps(false);
            setManagerAccess(false);
            setPermissions(new HashMap<String, LanguagePermission>() {{
                put("it", new LanguagePermission() {{
                    setWorkflowStepIds(Arrays.asList(313));
                }});
                put("de", new LanguagePermission() {{
                    setWorkflowStepIds("all");
                }});
            }});
            setRoles(Collections.singletonList(new TranslatorRole() {{
                setName(TranslatorRoleName.PROOFREADER);
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
        }};
        ResponseObject<ProjectMember> response = this.getUsersApi().replaceProjectMemberPermissions(this.projectId, this.memberId, request);
        assertNotNull(response);
        assertNotNull(response.getData());

        TranslatorRole responseRole = response.getData().getRoles().get(0);
        assertEquals(responseRole.getName(), TranslatorRoleName.PROOFREADER);
        assertFalse(responseRole.getPermissions().isAllLanguages());

        Map<String, LanguageAccessRule> responseRules = responseRole.getPermissions().getLanguagesAccess();
        assertFalse(responseRules.get("uk").isAllContent());
        assertEquals(responseRules.get("uk").getWorkflowStepIds(), Collections.singletonList(882));
        assertTrue(responseRules.get("it").isAllContent());
    }

    @Test
    public void deleteMemberFromProjectTest() {
        this.getUsersApi().deleteMemberFromProject(this.projectId, this.memberId);
    }

    @Test
    public void listUsersTest() {
        ResponseList<User> userResponseList = this.getUsersApi().listUsers(null, null, null, null, null);
        assertEquals(userResponseList.getData().size(), 1);
        assertEquals(userResponseList.getData().get(0).getData().getId(), userId);
    }

    @Test
    public void listUsersTest_orderByNull() {
        ResponseList<User> userResponseList = this.getUsersApi().listUsers(null, null, null, null, null, null);
        assertEquals(1, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void inviteUserTest() {
        InviteUserRequest request = new InviteUserRequest();
        request.setEmail(email);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setTimezone(timezone);
        ResponseObject<User> invitedUserResponseObject = this.getUsersApi().inviteUser(request);
        assertEquals(invitedUserResponseObject.getData().getId(), userId);
        assertEquals(invitedUserResponseObject.getData().getLastName(), name);
    }

    @Test
    public void getUserTest() {
        ResponseObject<User> user = this.getUsersApi().getUser(userId);
        assertEquals(user.getData().getId(), userId);
    }

    @Test
    public void deleteUserTest() {this.getUsersApi().deleteUser(userId);}

    @Test
    public void editUserTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/lastName");
        ResponseObject<User> userResponseObject = this.getUsersApi().editUser(userId, singletonList(request));
        assertEquals(userResponseObject.getData().getId(), userId);
        assertEquals(userResponseObject.getData().getLastName(), name);
    }

    @Test
    public void getAuthenticatedUserTest() {
        ResponseObject<User> user = this.getUsersApi().getAuthenticatedUser();
        assertEquals(user.getData().getId(), userId);
    }

    @Test
    public void listProjectMembersTest() {
        ResponseList<TeamMember> teamMemberResponseList = this.getUsersApi().listProjectMembers(projectId, null, null, null);
        assertEquals(teamMemberResponseList.getData().size(), 1);
        assertEquals(teamMemberResponseList.getData().get(0).getData().getId(), userId);
    }

    @Test
    public void listProjectMembersTest_orderByNull() {
        ResponseList<TeamMember> teamMemberResponseList = this.getUsersApi().listProjectMembers(projectId, null, null, null, null);
        assertEquals(1, teamMemberResponseList.getData().size());
        assertEquals(userId, teamMemberResponseList.getData().get(0).getData().getId());
    }

    @Test
    public void listProjectMembersTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<TeamMember> teamMemberResponseList = this.getUsersApi().listProjectMembers(projectId5, null, null, null, singletonList(orderById));
        assertEquals(2, teamMemberResponseList.getData().size());
        assertEquals(userId, teamMemberResponseList.getData().get(0).getData().getId());
        assertEquals(user2Id, teamMemberResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listProjectMembersTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<TeamMember> teamMemberResponseList = this.getUsersApi().listProjectMembers(projectId5, null, null, null, singletonList(orderById));
        assertEquals(2, teamMemberResponseList.getData().size());
        assertEquals(userId, teamMemberResponseList.getData().get(0).getData().getId());
        assertEquals(user2Id, teamMemberResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listProjectMembersTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<TeamMember> teamMemberResponseList = this.getUsersApi().listProjectMembers(projectId6, null, null, null, singletonList(orderById));
        assertEquals(2, teamMemberResponseList.getData().size());
        assertEquals(user2Id, teamMemberResponseList.getData().get(0).getData().getId());
        assertEquals(userId, teamMemberResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void getMemberInfoTest() {
        ResponseObject<TeamMember> user = this.getUsersApi().getMemberInfo(projectId, userId);
        assertEquals(user.getData().getId(), userId);
        assertEquals(3, user.getData().getPermissions().size());
    }
}
