package xyz.brawl.gamerise.repository.brawler;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;

public interface BrawlersCallBack {
    void onSuccessFromLocal(List<BrawlerEntry> brawler);
    void onFailureFromLocal(Exception exception);
    void onSuccessFromRemote(List<BrawlerEntry> brawler, long lastUpdate);
    void onFailureFromRemote(Exception exception);
}
