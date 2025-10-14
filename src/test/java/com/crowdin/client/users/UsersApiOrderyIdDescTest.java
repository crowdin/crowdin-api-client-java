package com.crowdin.client.users;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.SortOrder;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.users.model.User;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersApiOrderyIdDescTest extends TestClient {

    private final Long userId = 1L;
    private final Long user2Id = 2L;

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/users", HttpGet.METHOD_NAME, "api/users/listUsersOrderByIdDesc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }})
        );
    }

    @Test
    public void listUsersTest_orderByIdDesc() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");
        orderById.setOrderBy(SortOrder.DESC);

        ResponseList<User> userResponseList = this.getUsersApi().listUsers(
                null,
                null,
                null,
                null,
                null,
                singletonList(orderById)
        );

        assertEquals(2, userResponseList.getData().size());
        assertEquals(user2Id, userResponseList.getData().get(0).getData().getId());
        assertEquals(userId, userResponseList.getData().get(1).getData().getId());
    }
}
