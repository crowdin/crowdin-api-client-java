package com.crowdin.client.users;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.AddProjectTeamMemberRequest;
import com.crowdin.client.users.model.LanguagePermission;
import com.crowdin.client.users.model.ProjectTeamMembersResponse;
import com.crowdin.client.users.model.TeamMember;
import com.crowdin.client.users.model.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiTest extends TestClient {

    private final Long projectId = 12L;
    private final Long userId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpPost.METHOD_NAME, "api/users/addProjectTeamMember.json", "api/users/projectTeamMembers.json"),
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsers.json"),
                RequestMock.build(this.url + "/users/" + userId, HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/user", HttpGet.METHOD_NAME, "api/users/user.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members", HttpGet.METHOD_NAME, "api/users/listProjectMembers.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/members/" + userId, HttpGet.METHOD_NAME, "api/users/projectMember.json")
        );
    }

    @Test
    public void addProjectTeamMemberTest() {
        AddProjectTeamMemberRequest request = new AddProjectTeamMemberRequest();
        request.setUserIds(Collections.singletonList(userId));
        LanguagePermission languagePermission = new LanguagePermission();
        languagePermission.setWorkflowStepIds("all");
        request.setPermissions(Collections.singletonMap(
                "de", languagePermission
        ));
        ProjectTeamMembersResponse projectTeamMembersResponse = this.getUsersApi().addProjectTeamMember(projectId, request);
        assertEquals(projectTeamMembersResponse.getAdded().size(), 1);
        assertEquals(projectTeamMembersResponse.getAdded().get(0).getData().getId(), userId);
    }

    @Test
    public void listUsersTest() {
        ResponseList<User> userResponseList = this.getUsersApi().listUsers(null, null, null, null, null);
        assertEquals(userResponseList.getData().size(), 1);
        assertEquals(userResponseList.getData().get(0).getData().getId(), userId);
    }

    @Test
    public void getUserTest() {
        ResponseObject<User> user = this.getUsersApi().getUser(userId);
        assertEquals(user.getData().getId(), userId);
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
    }
}
