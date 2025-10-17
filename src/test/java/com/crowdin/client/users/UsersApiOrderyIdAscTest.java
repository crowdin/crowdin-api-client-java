package com.crowdin.client.users;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.*;
import org.apache.http.client.methods.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersApiOrderyIdAscTest extends TestClient {

    private final Long userId = 1L;
    private final Long user2Id = 2L;

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsersOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listUsersTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
        assertEquals(user2Id, userResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listUsersTest_orderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
        assertEquals(user2Id, userResponseList.getData().get(1).getData().getId());
    }

    @Test
    public void listUsersTest_newOrderByIdAsc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.ASC);

        ListUsersParams listUsersParams = new ListUsersParams();
        listUsersParams.setOrderBy(singletonList(orderById));

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(listUsersParams);

        assertEquals(2, userResponseList.getData().size());
        assertEquals(userId, userResponseList.getData().get(0).getData().getId());
        assertEquals(user2Id, userResponseList.getData().get(1).getData().getId());
    }
}
