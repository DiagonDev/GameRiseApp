package xyz.brawl.gamerise.repository.battlelog;

import java.util.List;

import xyz.brawl.gamerise.model.battle.Battle;

public interface BattleLogCallback {
    void onSuccessFromRemote(List<Battle> battles, long lastUpdate);
    void onFailureFromRemote(Exception exception);
    void onSuccessFromLocal(List<Battle> battles);
    void onFailureFromLocal(Exception exception);
}
