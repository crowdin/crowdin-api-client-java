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

public class TeamsApiListTeamsSearchByProjectIdTest extends TestClient {

    private final Long teamId = 1L;
    private final Long team2Id = 2L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/teams", HttpGet.METHOD_NAME, "api/teams/listTeamsOrderByIdAsc.json", new HashMap<String, String>() {{
                    put("projectIds", "1,2,3");
                }})
        );
    }

    @Test
    public void listTeamsTest_searchByProjectId() {
        List<Long> projectIds = Arrays.asList(1L, 2L, 3L);
        ListTeamsParams query = new ListTeamsParams();
        query.setProjectIds(projectIds);

        ResponseList<Team> teamResponseList = this.getTeamsApi().listTeams(query);
        assertEquals(2, teamResponseList.getData().size());
        assertEquals(teamId, teamResponseList.getData().get(0).getData().getId());
        assertEquals(team2Id, teamResponseList.getData().get(1).getData().getId());
    }
}
