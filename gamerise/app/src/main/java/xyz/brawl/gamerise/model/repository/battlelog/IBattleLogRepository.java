package xyz.brawl.gamerise.model.repository.battlelog;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;

public interface IBattleLogRepository {

    void fetchBattleLog(String playerTag, long lastUpdate);
}
