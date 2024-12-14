package xyz.brawl.gamerise.util;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;

public interface ResponseCallback {
    <T> void onSuccess(List<T> list, long lastUpdate);
    void onFailure(String errorMessage);
}
