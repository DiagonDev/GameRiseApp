package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;

public class BattleEntry {
    private List<TeamEntry> teams;
    private int trophyChange;
    private String ranked;
    private EventEntry event;

    public EventEntry getEvent() {
        return event;
    }

    public void setEvent(EventEntry event) {
        this.event = event;
    }

    public String getRanked() {
        return ranked;
    }

    public void setRanked(String ranked) {
        this.ranked = ranked;
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
