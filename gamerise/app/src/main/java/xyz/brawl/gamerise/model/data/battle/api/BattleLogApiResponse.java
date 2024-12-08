package xyz.brawl.gamerise.model.data.battle.api;

import java.util.List;

public class BattleLogApiResponse {
    private List<BattleLogEntry> item;

    public List<BattleLogEntry> getBattleResponseList() {
        return item;
    }

    public void setBattleResponseList(List<BattleLogEntry> item) {
        this.item = item;
    }
}
