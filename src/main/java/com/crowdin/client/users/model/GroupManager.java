package com.crowdin.client.users.model;

import com.crowdin.client.teams.model.GroupTeam;
import com.crowdin.client.teams.model.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupManager {
    private Long id;
    private User user;
    /**
     * @deprecated the API returns plain team objects, use {@link #teamList} instead
     */
    @Deprecated
    @JsonIgnore
    private List<GroupTeam> teams;
    @JsonProperty("teams")
    private List<Team> teamList;

    /**
     * @return teams wrapped into {@link GroupTeam} objects (id is the team id)
     * @deprecated use {@link #teamList} instead
     */
    @Deprecated
    public List<GroupTeam> getTeams() {
        if (this.teamList == null) {
            return null;
        }
        List<GroupTeam> result = new ArrayList<>();
        for (Team team : this.teamList) {
            GroupTeam groupTeam = new GroupTeam();
            groupTeam.setId(team == null ? null : team.getId());
            groupTeam.setTeam(team);
            result.add(groupTeam);
        }
        return result;
    }

    /**
     * @param teams group teams
     * @deprecated use {@link #teamList} instead
     */
    @Deprecated
    public void setTeams(List<GroupTeam> teams) {
        if (teams == null) {
            this.teamList = null;
            return;
        }
        List<Team> result = new ArrayList<>();
        for (GroupTeam groupTeam : teams) {
            if (groupTeam == null) {
                result.add(null);
            } else if (groupTeam.getTeam() != null) {
                result.add(groupTeam.getTeam());
            } else {
                Team team = new Team();
                team.setId(groupTeam.getId());
                result.add(team);
            }
        }
        this.teamList = result;
    }
}
