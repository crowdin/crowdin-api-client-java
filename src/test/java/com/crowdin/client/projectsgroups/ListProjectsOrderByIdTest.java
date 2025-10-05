package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.*;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ListProjectsOrderByIdTest extends TestClient {
    private final Long projectId = 8L;
    private final String projectName = "Knowledge Base#1";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects", HttpGet.METHOD_NAME, "api/projectsgroups/listProjectsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20desc");
                }})
        );
    }

    @Test
    public void listProjectsTest() {
        OrderByField orderByField = new OrderByField();
        orderByField.setFieldName("id");
        orderByField.setOrderBy(SortOrder.DESC);

        List<OrderByField> orderByFields = singletonList(orderByField);

        ResponseList<? extends Project> projectResponseList = this.getProjectsGroupsApi().listProjects(null, null, null, null, orderByFields);
        assertEquals(2, projectResponseList.getData().size());
        assertEquals(8, projectResponseList.getData().get(0).getData().getId());
        assertEquals(9, projectResponseList.getData().get(1).getData().getId());
        assertEquals(projectResponseList.getData().get(0).getData().getId(), projectId);
        assertEquals(projectResponseList.getData().get(0).getData().getName(), projectName);
    }
}
