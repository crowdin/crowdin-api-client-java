package com.crowdin.client.teams;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.teams.model.*;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class TeamsApiListTeamsOrderByIdAscTest extends TestClient {

    private final Long teamId = 1L;
    private final Long team2Id = 2L;
    private final String name = "French";
    private final String name2 = "French-#2";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/teams", HttpGet.METHOD_NAME, "api/teams/listTeamsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("orderBy", "id%20asc");
                }})
        );
    }

    @Test
    public void listTeamsTest_orderByIdNull() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");

        ResponseList<Team> teamResponseList = this.getTeamsApi().listTeams(null, null, singletonList(orderBy));
        assertEquals(2, teamResponseList.getData().size());
        assertEquals(teamId, teamResponseList.getData().get(0).getData().getId());
        assertEquals(name, teamResponseList.getData().get(0).getData().getName());
        assertEquals(team2Id, teamResponseList.getData().get(1).getData().getId());
        assertEquals(name2, teamResponseList.getData().get(1).getData().getName());
    }

    @Test
    public void listTeamsTest_orderByIdAsc() {
        OrderByField orderBy = new OrderByField();
        orderBy.setFieldName("id");
        orderBy.setOrderBy(SortOrder.ASC);

        ResponseList<Team> teamResponseList = this.getTeamsApi().listTeams(null, null, singletonList(orderBy));
        assertEquals(2, teamResponseList.getData().size());
        assertEquals(teamId, teamResponseList.getData().get(0).getData().getId());
        assertEquals(name, teamResponseList.getData().get(0).getData().getName());
        assertEquals(team2Id, teamResponseList.getData().get(1).getData().getId());
        assertEquals(name2, teamResponseList.getData().get(1).getData().getName());
    }
}
