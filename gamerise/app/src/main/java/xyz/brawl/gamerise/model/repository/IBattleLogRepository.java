package xyz.brawl.gamerise.model.repository;

public interface IBattleLogRepository {

    void fetchBattleLog(String playerTag, long lastUpdate);
}
