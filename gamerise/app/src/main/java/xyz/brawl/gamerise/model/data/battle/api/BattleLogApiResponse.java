package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;

public class BattleLogApiResponse {
    private List<BattleLogEntry> items;

    public List<BattleLogEntry> getBattleResponseList() {
        return items;
    }

    public void setBattleResponseList(List<BattleLogEntry> items) {
        this.items = items;
    }
}
