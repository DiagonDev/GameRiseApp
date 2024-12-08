package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;


public class TeamEntry {
    private List<PlayerEntry> players;

    public List<PlayerEntry> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntry> players) {
        this.players = players;
    }
}
