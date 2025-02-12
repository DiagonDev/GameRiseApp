package xyz.brawl.gamerise.model.battle;

import java.util.List;

public class BattleLogApiResponse {
    private List<BattleLogEntry> items;

    public BattleLogApiResponse(List<BattleLogEntry> items) {
        this.items = items;
    }
    public List<BattleLogEntry> getBattleResponseList() {
        return items;
    }

    @Deprecated
    public void setBattleResponseList(List<BattleLogEntry> items) {
        this.items = items;
    }
}
