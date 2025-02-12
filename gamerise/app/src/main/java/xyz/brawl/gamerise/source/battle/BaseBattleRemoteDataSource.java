package xyz.brawl.gamerise.source.battle;

import xyz.brawl.gamerise.repository.battlelog.BattleLogCallback;

public abstract class BaseBattleRemoteDataSource {
    protected BattleLogCallback battleLogCallback;
    public void setBattleLogCallback(BattleLogCallback battleLogCallback) {
        this.battleLogCallback = battleLogCallback;
    }
    public abstract void getBattleLog(String tagId);
}
