package xyz.brawl.gamerise.source.stats;

import xyz.brawl.gamerise.model.stat.Stat;
import xyz.brawl.gamerise.repository.stats.StatsCallBack;

public abstract class BaseStatsLocalDataSource {
    protected StatsCallBack statsCallBack;

    public void setStatsCallback(StatsCallBack statsCallBack) {
        this.statsCallBack = statsCallBack;
    }

    public abstract void getStats(String tagId);

    public abstract void insertStats(Stat stats);
}
