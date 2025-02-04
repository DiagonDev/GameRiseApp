package xyz.brawl.gamerise.model.repository.stats;

import xyz.brawl.gamerise.model.data.stat.Stat;

public interface StatsCallBack {
    //TODO: leo deve modificare qui
    void onSuccessFromRemote(Stat stats, long lastUpdate);
    void onFailureFromRemote(String errorMessage);

    //qui è giusto
    void onSuccessFromLocal(Stat stats);
    void onFailureFromLocal(Exception exception);
}
