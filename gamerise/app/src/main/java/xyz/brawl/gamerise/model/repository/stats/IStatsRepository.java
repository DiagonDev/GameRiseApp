package xyz.brawl.gamerise.model.repository.stats;

public interface IStatsRepository {

    void fetchStats(String playerTag, long lastUpdate);
}
