package com.crowdin.client.tasks;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.Task;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTasksOrderByIdTest extends TestClient {

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/user/tasks", HttpGet.METHOD_NAME, "api/tasks/listTasksSortByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listUserTasksTest_orderByIdNull() {
        OrderByField orderById = new OrderByField();
        orderById.setFieldName("id");

        ResponseList<Task> taskResponseList = this.getTasksApi().listUserTasks(null, null, null, null, singletonList(orderById));
        assertEquals(2, taskResponseList.getData().size());
        assertEquals(1, taskResponseList.getData().get(0).getData().getId());
        assertEquals(2, taskResponseList.getData().get(1).getData().getId());
    }
}
