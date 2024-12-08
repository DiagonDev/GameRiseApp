package xyz.brawl.gamerise.model.data.battle.api;

import xyz.brawl.gamerise.model.data.brawler.BrawlerV2;

public class PlayerEntry {
    private BrawlerV2 brawler;
    private String tag;

    public BrawlerV2 getBrawler() {
        return brawler;
    }

    public void setBrawler(BrawlerV2 brawler) {
        this.brawler = brawler;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
