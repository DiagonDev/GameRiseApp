package xyz.brawl.gamerise.model.repository.brawler;

import java.util.List;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;

public interface BrawlersCallBack {
    void onSuccessFromLocal(List<BrawlerEntry> brawler);
    void onFailureFromLocal(Exception exception);

    void onSuccessFromRemote(Object o, long lastUpdate);

    void onFailureFromRemote(Exception exception);
}
