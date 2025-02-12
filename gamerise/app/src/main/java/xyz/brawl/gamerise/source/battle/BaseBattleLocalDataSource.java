package xyz.brawl.gamerise.source.battle;

import java.util.List;

import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.repository.battlelog.BattleLogCallback;

public abstract class BaseBattleLocalDataSource {
    protected BattleLogCallback battleLogCallback;

    public void setBattleLogCallback(BattleLogCallback battleLogCallback) {
        this.battleLogCallback = battleLogCallback;
    }

    public abstract void getBattles(String tagId);

    public abstract void insertBattles(List<Battle> battleList);
}
