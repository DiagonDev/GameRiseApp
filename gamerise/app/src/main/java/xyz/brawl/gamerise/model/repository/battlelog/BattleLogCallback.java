package xyz.brawl.gamerise.model.repository.battlelog;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

public interface BattleLogCallback {

    //TODO: leo deve modificare qui
    void onSuccessFromRemote(List<Battle> battles, long lastUpdate);
    void onFailureFromRemote(String errorMessage);
    //qui è giusto
    void onSuccessFromLocal(List<Battle> battles);
    void onFailureFromLocal(Exception exception);
}
