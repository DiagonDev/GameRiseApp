package xyz.brawl.gamerise.model.repository.player;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;

public interface PlayerCallBack {
    void onSuccessFromRemote(PlayerApiResponse playerApiResponse, long lastUpdate);
    void onFailureFromRemote(Exception exception);
}
