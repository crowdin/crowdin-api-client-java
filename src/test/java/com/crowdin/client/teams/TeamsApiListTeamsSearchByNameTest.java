package com.crowdin.client.teams;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.teams.model.ListTeamsParams;
import com.crowdin.client.teams.model.Team;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamsApiListTeamsSearchByNameTest extends TestClient {

    private final Long teamId = 1L;
    private final String name = "French";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/teams", HttpGet.METHOD_NAME, "api/teams/listTeams.json", new HashMap<String, String>() {{
                    put("search", "French");
                }})
        );
    }

    @Test
    public void listTeamsTest_searchByName() {
        ListTeamsParams query = new ListTeamsParams();
        query.setSearch("French");

        ResponseList<Team> teamResponseList = this.getTeamsApi().listTeams(query);
        assertEquals(1, teamResponseList.getData().size());
        assertEquals(teamId, teamResponseList.getData().get(0).getData().getId());
        assertEquals(name, teamResponseList.getData().get(0).getData().getName());

    }
}
