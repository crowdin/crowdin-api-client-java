package com.crowdin.client.users;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long projectId2 = 13L;
    private final Long userId = 1L;
    private final Long memberId = 3L;

    private final String name = "Smith";

    private final String email = "john@example.com";

    private final String firstName = "Jon";

    private final String lastName = "Doe";

    private final String timezone = "America/New_York";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(String.format("%s/projects/%d/members", this.url, projectId2), HttpGet.METHOD_NAME, "api/users/listProjectMembersEnterprise.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpPost.METHOD_NAME, "api/users/addProjectMember.json", "api/users/projectTeamMembers.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpGet.METHOD_NAME, "api/users/getProjectMemberResponse.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpPut.METHOD_NAME, "api/users/replaceProjectMemberPermissionsRequest.json", "api/users/replaceProjectMemberPermissionsResponse.json"),
                RequestMock.build(String.format("%s/projects/%d/members/%d", this.url, projectId, memberId), HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsers.json"),
                RequestMock.build(this.url + "/users/" + userId, HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/user", HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembers.json"),
                RequestMock.build(this.url + "/users", HttpPost.METHOD_NAME, "api/users/invitedUser.json", "api/users/user.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members/" + userId, HttpGet.METHOD_NAME, "api/users/projectMember.json"),
                RequestMock.build(this.url + "/users/" + userId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/users/" + userId, HttpPatch.METHOD_NAME, "api/users/editUser.json", "api/users/user.json")
        );
    }

    @Test
    public void listProjectTeamMembersEnterpriseTest() {
        ResponseList<ProjectMember> responseList = this.getUsersApi().listProjectMembersEnterprise(this.projectId2, null, null, null, null, null);
        assertNotNull(responseList);
        assertEquals(responseList.getData().size(), 1);
        assertEquals(responseList.getData().get(0).getData().getId(), this.userId);
    }

    @Test
    public void addProjectTeamMemberTest() {
        AddProjectMemberRequest request = new AddProjectMemberRequest();
        request.setUserIds(singletonList(userId));
        LanguagePermission languagePermission = new LanguagePermission();
        languagePermission.setWorkflowStepIds("all");
        request.setPermissions(Collections.singletonMap(
                "de", languagePermission
        ));
        ProjectMembersResponse projectMembersResponse = this.getUsersApi().addProjectMember(projectId, request);
        assertEquals(projectMembersResponse.getAdded().size(), 1);
        assertEquals(projectMembersResponse.getAdded().get(0).getData().getId(), userId);
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
        }};
        ResponseObject<ProjectMember> response = this.getUsersApi().replaceProjectMemberPermissions(this.projectId, this.memberId, request);
        assertNotNull(response);
        assertNotNull(response.getData());
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
    public void getMemberInfoTest() {
        ResponseObject<TeamMember> user = this.getUsersApi().getMemberInfo(projectId, userId);
        assertEquals(user.getData().getId(), userId);
        assertEquals(3, user.getData().getPermissions().size());
    }
}
