package xyz.brawl.gamerise.repository.stats;

import xyz.brawl.gamerise.model.stat.Stat;

public interface StatsCallBack {
    void onSuccessFromRemote(Stat stats, long lastUpdate);
    void onFailureFromRemote(Exception exception);

    //qui è giusto
    void onSuccessFromLocal(Stat stats);
    void onFailureFromLocal(Exception exception);
}
