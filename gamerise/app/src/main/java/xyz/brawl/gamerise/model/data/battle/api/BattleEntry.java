package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;

public class BattleEntry {
    private List<TeamEntry> teams;
    private int trophyChange;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTrophyChange() {
        return trophyChange;
    }

    public void setTrophyChange(int trophyChange) {
        this.trophyChange = trophyChange;
    }

    public List<TeamEntry> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntry> teams) {
        this.teams = teams;
    }
}
