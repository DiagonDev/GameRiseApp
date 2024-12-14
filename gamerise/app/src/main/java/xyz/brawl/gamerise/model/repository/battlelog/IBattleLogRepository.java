package xyz.brawl.gamerise.model.repository.battlelog;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;

public interface IBattleLogRepository {

    BattleLogApiResponse fetchBattleLog(String playerTag, long lastUpdate);
}
