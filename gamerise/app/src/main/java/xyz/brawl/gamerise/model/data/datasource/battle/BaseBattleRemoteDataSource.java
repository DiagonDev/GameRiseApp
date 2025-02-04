package xyz.brawl.gamerise.model.data.datasource.battle;

import xyz.brawl.gamerise.model.repository.battlelog.BattleLogCallback;

public abstract class BaseBattleRemoteDataSource {
    protected BattleLogCallback battleLogCallback;
    public void setBattleLogCallback(BattleLogCallback battleLogCallback) {
        this.battleLogCallback = battleLogCallback;
    }
    public abstract void getBattleLog(String tagId);
}
