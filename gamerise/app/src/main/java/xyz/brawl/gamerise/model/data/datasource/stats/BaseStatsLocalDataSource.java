package xyz.brawl.gamerise.model.data.datasource.stats;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerCallback;
import xyz.brawl.gamerise.model.repository.stats.StatsCallBack;

public abstract class BaseStatsLocalDataSource {
    protected StatsCallBack statsCallBack;

    public void setStatsCallback(StatsCallBack statsCallback) {
        this.statsCallBack = statsCallBack;
    }

    public abstract void getStats(String tagId);

    public abstract void insertStats(Stat stats, String tagId);
}
