package xyz.brawl.gamerise.model.data.datasource.brawler;

import xyz.brawl.gamerise.model.repository.brawler.BrawlersCallBack;

public abstract class BaseBrawlersRemoteDataSource {
    protected BrawlersCallBack brawlersCallBack;
    public void setBrawlerCallBack(BrawlersCallBack brawlersCallBack) {
        this.brawlersCallBack = brawlersCallBack;
    }
    public abstract void getBrawlerList(String tagId);
}
