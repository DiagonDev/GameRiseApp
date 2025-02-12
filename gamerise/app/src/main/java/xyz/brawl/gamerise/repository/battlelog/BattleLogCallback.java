package xyz.brawl.gamerise.repository.battlelog;

import java.util.List;

import xyz.brawl.gamerise.model.battle.Battle;

public interface BattleLogCallback {

    //TODO: leo deve modificare qui
    void onSuccessFromRemote(List<Battle> battles, long lastUpdate);
    void onFailureFromRemote(Exception exception);
    //qui è giusto
    void onSuccessFromLocal(List<Battle> battles);
    void onFailureFromLocal(Exception exception);
}
