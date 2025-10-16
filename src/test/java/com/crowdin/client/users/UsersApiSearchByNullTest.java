package com.crowdin.client.users;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.User;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiSearchByNullTest extends TestClient {

    private final Long userId = 1L;
    private final String username = "john_smith";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsers.json")
        );
    }

    @Test
    public void listUsersTest_Null() {

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(null);

        assertEquals(1, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
        assertEquals(username, userResponseList.getData().get(0).getData().getUsername());
    }
}
