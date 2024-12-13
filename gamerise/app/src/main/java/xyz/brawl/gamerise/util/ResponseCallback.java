package xyz.brawl.gamerise.util;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;

public interface ResponseCallback {
    void onSuccess(List<BattleLogEntry> battleLogEntries, long lastUpdate);
    void onFailure(String errorMessage);
}
