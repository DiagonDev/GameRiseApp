package xyz.brawl.gamerise.model.battle;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;

public class PlayerEntry {
    private BrawlerEntry brawler;
    private String tag;

    public BrawlerEntry getBrawler() {
        return brawler;
    }

    public void setBrawler(BrawlerEntry brawler) {
        this.brawler = brawler;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
