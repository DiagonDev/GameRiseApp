package xyz.brawl.gamerise.model.repository.stats;

import xyz.brawl.gamerise.model.data.stat.Stat;

public interface StatsCallBack {
    void onSuccessFromRemote(Stat stats, long lastUpdate);
    void onFailureFromRemote(Exception exception);

    //qui è giusto
    void onSuccessFromLocal(Stat stats);
    void onFailureFromLocal(Exception exception);
}
