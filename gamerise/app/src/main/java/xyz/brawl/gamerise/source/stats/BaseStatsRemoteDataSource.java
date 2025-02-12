package xyz.brawl.gamerise.source.stats;

import xyz.brawl.gamerise.repository.stats.StatsCallBack;

public abstract class BaseStatsRemoteDataSource {
    protected StatsCallBack statsCallBack;
    public void setStatsCallback(StatsCallBack statsCallBack) {
        this.statsCallBack = statsCallBack;
    }

    public abstract void getStats(String tagId);
}
