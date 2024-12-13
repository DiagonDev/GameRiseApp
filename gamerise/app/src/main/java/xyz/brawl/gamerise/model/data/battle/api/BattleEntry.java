package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;

public class BattleEntry {
    private List<List<PlayerEntry>> teams;
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

    public List<List<PlayerEntry>> getTeams() {
        return teams;
    }

    public void setTeams(List<List<PlayerEntry>> teams) {
        this.teams = teams;
    }
}
