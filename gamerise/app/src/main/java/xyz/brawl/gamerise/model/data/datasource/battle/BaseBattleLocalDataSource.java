package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogCallback;

public abstract class BaseBattleLocalDataSource {
    protected BattleLogCallback battleLogCallback;

    public void setBattleLogCallback(BattleLogCallback battleLogCallback) {
        this.battleLogCallback = battleLogCallback;
    }

    public abstract void getBattles();

    public abstract void deleteBattles(Battle battle);

    public abstract void insertBattles(List<Battle> battleList);
}
