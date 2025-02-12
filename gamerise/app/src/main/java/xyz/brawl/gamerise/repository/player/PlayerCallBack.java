package xyz.brawl.gamerise.repository.player;

import xyz.brawl.gamerise.model.player.PlayerApiResponse;

public interface PlayerCallBack {
    void onSuccessFromRemote(PlayerApiResponse playerApiResponse, long lastUpdate);
    void onFailureFromRemote(Exception exception);
}
