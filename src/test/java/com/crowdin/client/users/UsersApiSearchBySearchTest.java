package com.crowdin.client.users;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.ListUsersParams;
import com.crowdin.client.users.model.User;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiSearchBySearchTest extends TestClient {

    private final Long userId = 1L;
    private final String username = "john_smith";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsers.json", new HashMap<String, String>() {{
                    put("search", "john_smith");
                }})
        );
    }

    @Test
    public void listUsersTest_newOrderByIdAsc() {
        ListUsersParams listUsersParams = new ListUsersParams();
        listUsersParams.setSearch("john_smith");

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(listUsersParams);

        assertEquals(1, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
        assertEquals(username, userResponseList.getData().get(0).getData().getUsername());
    }
}
