package xyz.brawl.gamerise.source.brawler;

import xyz.brawl.gamerise.repository.brawler.BrawlersCallBack;

public abstract class BaseBrawlersRemoteDataSource {
    protected BrawlersCallBack brawlersCallBack;
    public void setBrawlerCallBack(BrawlersCallBack brawlersCallBack) {
        this.brawlersCallBack = brawlersCallBack;
    }
    public abstract void getBrawlerList(String tagId);
}
