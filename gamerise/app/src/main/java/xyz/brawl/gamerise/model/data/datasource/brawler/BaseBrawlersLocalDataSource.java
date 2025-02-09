package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersCallBack;
public abstract class BaseBrawlersLocalDataSource {
    protected BrawlersCallBack brawlersCallBack;
    public void setBrawlerCallBack(BrawlersCallBack brawlersCallBack) {
        this.brawlersCallBack = brawlersCallBack;
    }
    public abstract void getBrawlers(String tagId);
    public abstract void insertBrawlers(List<BrawlerEntry> brawlerList);
}
