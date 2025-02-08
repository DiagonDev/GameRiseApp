package xyz.brawl.gamerise.model.repository.player;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;

public interface PlayerCallBack {
    void onSuccessFromLocal(String namePlayer);
    void onFailureFromLocal(Exception exception);

    void onSuccessFromRemote(String namePlayer, long lastUpdate);
    void onFailureFromRemote(Exception exception);
}
