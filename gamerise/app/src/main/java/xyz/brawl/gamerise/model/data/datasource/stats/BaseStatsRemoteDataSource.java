package xyz.brawl.gamerise.model.data.datasource.stats;

import xyz.brawl.gamerise.model.repository.battlelog.BattleLogCallback;
import xyz.brawl.gamerise.model.repository.stats.StatsCallBack;

public abstract class BaseStatsRemoteDataSource {
    protected StatsCallBack statsCallBack;
    public void setStatsCallBack(StatsCallBack statsCallBack) {
        this.statsCallBack = statsCallBack;
    }
    public abstract void getStats(String tagId);
}
