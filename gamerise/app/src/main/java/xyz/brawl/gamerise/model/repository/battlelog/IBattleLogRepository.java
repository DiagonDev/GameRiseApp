package xyz.brawl.gamerise.model.repository.battlelog;

public interface IBattleLogRepository {

    void fetchBattleLog(String playerTag, long lastUpdate);
}
