package xyz.brawl.gamerise.repository.player;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;

public interface PlayerCallBack {
    void onSuccessFromRemote(PlayerApiResponse playerApiResponse, long lastUpdate);
    void onFailureFromRemote(Exception exception);

    void onSuccessFromLocal(List<BrawlerEntry> brawlerList, List<StarPowerEntry> starPowerList, List<GadgetEntry> gadgetList);
}
