package xyz.brawl.gamerise.model.battle;

import java.util.List;

public class BattleEntry {
    private List<List<PlayerEntry>> teams;
    //json battleLog non è sempre uguale, a volte ho teams a volte ho players
    private List<PlayerEntry> players;

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

    public List<PlayerEntry> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntry> players) {
        this.players = players;
    }

    public void setTeams(List<List<PlayerEntry>> teams) {
        this.teams = teams;
    }
}
