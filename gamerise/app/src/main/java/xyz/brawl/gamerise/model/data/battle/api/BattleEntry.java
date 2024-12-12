package xyz.brawl.gamerise.model.data.battle.api;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.List;

//@Entity
public class BattleEntry {
    //@PrimaryKey
    public int uid;

    private List<TeamEntry> teams;
    private int trophyChange;
    private String type;
    private EventEntry event;

    public EventEntry getEvent() {
        return event;
    }

    public void setEvent(EventEntry event) {
        this.event = event;
    }

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
